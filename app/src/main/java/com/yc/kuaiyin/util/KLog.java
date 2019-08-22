package com.yc.kuaiyin.util;

import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.Random;

public class KLog {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String NULL_TIPS = "Log with null object";

    private static final String DEFAULT_MESSAGE = "Log default execute";
    private static final String PARAM = "Param";
    private static final String NULL = "null";
    private static final String TAG_DEFAULT = KLog.class.getSimpleName();
    private static final String SUFFIX = ".java";

    //所有的TAG的统一前缀
    private static final String TAG_PREFIX = "JDA_";

    public static final int JSON_INDENT = 4;
    public static final int V = Log.VERBOSE;
    public static final int D = Log.DEBUG;
    public static final int I = Log.INFO;
    public static final int W = Log.WARN;
    public static final int E = Log.ERROR;

    private static final int JSON = 0x7;
    private static final int XML = 0x8;

    private static final int STACK_TRACE_INDEX = 5;

    private static String mGlobalTag;
    private static boolean IS_SHOW_LOG = true;

    private KLog() {
    }

    public static void init(boolean isShowLog) {
        IS_SHOW_LOG = isShowLog;
    }

    public static void init(boolean isShowLog, String tag) {
        IS_SHOW_LOG = isShowLog;
        mGlobalTag = tag;
    }

    public static void v() {
        printLog(V, null, DEFAULT_MESSAGE);
    }

    public static void v(Object msg) {
        printLog(V, null, msg);
    }

    public static void v(String tag, Object... objects) {
        printLog(V, tag, objects);
    }

    public static void d() {
        printLog(D, null, DEFAULT_MESSAGE);
    }

    public static void d(Object msg) {
        printLog(D, null, msg);
    }

    public static void d(String tag, Object... objects) {
        printLog(D, tag, objects);
    }

    public static void i() {
        printLog(I, null, DEFAULT_MESSAGE);
    }

    public static void i(Object msg) {
        printLog(I, null, msg);
    }

    public static void i(String tag, Object... objects) {
        printLog(I, tag, objects);
    }

    public static void w() {
        printLog(W, null, DEFAULT_MESSAGE);
    }

    public static void w(Object msg) {
        printLog(W, null, msg);
    }

    public static void w(String tag, Object... objects) {
        printLog(W, tag, objects);
    }

    public static void e() {
        printLog(E, null, DEFAULT_MESSAGE);
    }

    public static void e(Object msg) {
        printLog(E, null, msg);
    }

    public static void e(String tag, Object... objects) {
        printLog(E, tag, objects);
    }

    public static void json(String jsonFormat) {
        printLog(JSON, null, jsonFormat);
    }

    public static void json(String tag, String jsonFormat) {
        printLog(JSON, tag, jsonFormat);
    }

    public static void xml(String xml) {
        printLog(XML, null, xml);
    }

    public static void xml(String tag, String xml) {
        printLog(XML, tag, xml);
    }

    public static void file(File targetDirectory, Object msg) {
        printFile(null, targetDirectory, null, msg);
    }

    public static void file(String tag, File targetDirectory, Object msg) {
        printFile(tag, targetDirectory, null, msg);
    }

    public static void file(String tag, File targetDirectory, String fileName, Object msg) {
        printFile(tag, targetDirectory, fileName, msg);
    }

    private static void printLog(int priority, String tagStr, Object... objects) {
        String[] contents = wrapperContent(tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];
        switch (priority) {
            case V:
            case D:
            case I:
            case W:
            case E:
                if (objects != null && objects.length == 1 && objects[0] instanceof Throwable) {
                    Throwable e = (Throwable) objects[0];
                    if (priority == E) {
                        Log.e(tag, e.getMessage(), e);
                    } else {
                        Log.w(tag, e.getMessage(), e);
                    }
//                    LogSaver.saveLog(tag, msg, priority);
                    printSub(priority, tagStr, e.getMessage());
                    break;
                }
                BaseLog.printDefault(priority, tag, headString + msg);
                break;
            case JSON:
                JsonLog.printJson(tag, msg, headString);
                break;
            case XML:
                XmlLog.printXml(tag, msg, headString);
                break;
            default:
                break;
        }
    }

    private static void printFile(String tagStr, File targetDirectory, String fileName, Object objectMsg) {
        if (!IS_SHOW_LOG) {
            return;
        }
        String[] contents = wrapperContent(tagStr, objectMsg);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];
        FileLog.printFile(tag, targetDirectory, fileName, headString, msg);
    }

    private static String[] wrapperContent(String tagStr, Object... objects) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement targetElement = stackTrace[STACK_TRACE_INDEX];
        String className = targetElement.getClassName();
        String[] classNameInfo = className.split("\\.");
        if (classNameInfo.length > 0) {
            className = classNameInfo[classNameInfo.length - 1] + SUFFIX;
        }
        if (className.contains("$")) {
            className = className.split("\\$")[0] + SUFFIX;
        }
        String methodName = targetElement.getMethodName();
        if (methodName.contains("$")) {
            //compat kotlin internal function, eg: onPlay$base_lib
            methodName = methodName.substring(0, methodName.indexOf("$"));
        }
        int lineNumber = targetElement.getLineNumber();
        if (lineNumber < 0) {
            lineNumber = 0;
        }
        String methodNameShort = methodName;//.substring(0, 1).toUpperCase() + methodName.substring(1);
        String tag = (tagStr == null ? className : tagStr);
        if (TextUtils.isEmpty(mGlobalTag) && TextUtils.isEmpty(tag)) {
            tag = TAG_DEFAULT;
        } else if (!TextUtils.isEmpty(mGlobalTag)) {
            tag = mGlobalTag;
        }
        tag = TAG_PREFIX + tag;
        String msg = (objects == null) ? NULL_TIPS : getObjectsString(objects);
        String headString = "(" + className + ":" + lineNumber + ") " + methodNameShort + " | ";
        return new String[]{tag, msg, headString};
    }

    private static String getObjectsString(Object... objects) {
        if (objects.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            for (int i = 0; i < objects.length; i++) {
                Object object = objects[i];
                if (object == null) {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(NULL)
                            .append("\n");
                } else {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ")
                            .append(object.toString()).append("\n");
                }
            }
            return stringBuilder.toString();
        } else {
            Object object = objects[0];
            return object == null ? NULL : object.toString();
        }
    }

    public static class BaseLog {

        public static void printDefault(int priority, String tag, String msg) {
//            LogSaver.saveLog(tag, msg, priority);
            int index = 0;
            int maxLength = 4000;
            int countOfSub = msg.length() / maxLength;
            if (countOfSub > 0) {
                for (int i = 0; i < countOfSub; i++) {
                    String sub = msg.substring(index, index + maxLength);
                    printSub(priority, tag, sub);
                    index += maxLength;
                }
                printSub(priority, tag, msg.substring(index, msg.length()));
            } else {
                printSub(priority, tag, msg);
            }
        }
    }

    private static void printSub(int priority, String tag, String sub) {
        if (IS_SHOW_LOG) {
            Log.println(priority, tag, sub == null ? "null" : sub);
        }
    }

    public static class FileLog {

        public static void printFile(String tag, File targetDirectory, String fileName, String headString, String msg) {
            fileName = (fileName == null) ? getFileName() : fileName;
            if (save(targetDirectory, fileName, msg)) {
                Log.d(tag, headString + " save log success ! location is >>>" + targetDirectory.getAbsolutePath() + "/"
                        + fileName);
            } else {
                Log.e(tag, headString + "save log fails !");
            }
        }

        private static boolean save(File dic, String fileName, String msg) {
            File file = new File(dic, fileName);
            try {
                OutputStream outputStream = new FileOutputStream(file);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                outputStreamWriter.write(msg);
                outputStreamWriter.flush();
                outputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        private static String getFileName() {
            Random random = new Random();
            return "JLog_" + Long.toString(System.currentTimeMillis() + random.nextInt(10000)).substring(4) + ".txt";
        }

    }

    public static class JsonLog {

        public static void printJson(String tag, String msg, String headString) {
            String message;
            try {
                if (msg.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(msg);
                    message = jsonObject.toString(JSON_INDENT);
                } else if (msg.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(msg);
                    message = jsonArray.toString(JSON_INDENT);
                } else {
                    message = msg;
                }
            } catch (JSONException e) {
                message = msg;
            }
            Util.printLine(tag, true);
            message = headString + LINE_SEPARATOR + message;
            String[] lines = message.split(LINE_SEPARATOR);
            for (String line : lines) {
                Log.d(tag, "║ " + line);
            }
            Util.printLine(tag, false);
        }
    }

    public static class XmlLog {

        public static void printXml(String tag, String xml, String headString) {
            if (xml != null) {
                xml = formatXML(xml);
                xml = headString + "\n" + xml;
            } else {
                xml = headString + NULL_TIPS;
            }
            Util.printLine(tag, true);
            String[] lines = xml.split(KLog.LINE_SEPARATOR);
            for (String line : lines) {
                if (!Util.isEmpty(line)) {
                    Log.d(tag, "║ " + line);
                }
            }
            Util.printLine(tag, false);
        }

        public static String formatXML(String inputXML) {
            try {
                Source xmlInput = new StreamSource(new StringReader(inputXML));
                StreamResult xmlOutput = new StreamResult(new StringWriter());
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.transform(xmlInput, xmlOutput);
                return xmlOutput.getWriter().toString().replaceFirst(">", ">\n");
            } catch (Exception e) {
                e.printStackTrace();
                return inputXML;
            }
        }

    }

    public static class Util {

        public static boolean isEmpty(String line) {
            return TextUtils.isEmpty(line) || line.equals("\n") || line.equals("\t") || TextUtils.isEmpty(line.trim());
        }

        public static void printLine(String tag, boolean isTop) {
            if (isTop) {
                Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
            } else {
                Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
            }
        }

    }

}
