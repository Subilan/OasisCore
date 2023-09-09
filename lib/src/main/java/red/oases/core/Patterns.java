package red.oases.core;

import java.util.regex.Pattern;

/**
 * 类 <b>Patterns</b><br/>
 * <p>字符串相关验证和提取</p>
 */
public class Patterns {
    public static final Pattern layerRange = Pattern.compile("^(\\d+)-(\\d+)$");
    public static final Pattern layerBase = Pattern.compile("^(\\d+)\\+$");
    public static final Pattern effectList = Pattern.compile("^([\\u4e00-\\u9fa5]+\\s?\\d;)+(?!$)");
    public static final Pattern effect = Pattern.compile("^([\\u4e00-\\u9fa5]+)\\s?(\\d)$");

    public static boolean isEffectList(String listLike) {
        return effectList.matcher(listLike).matches();
    }

    public static boolean isLayerRange(String rangeLike) {
        return layerRange.matcher(rangeLike).matches();
    }

    public static boolean isLayerBase(String baseLike) {
        return layerBase.matcher(baseLike).matches();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static int[] getRange(String rangeLike) {
        var matcher = layerRange.matcher(rangeLike);
        matcher.matches();
        return new int[]{
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2))
        };
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static int getBase(String baseLike) {
        var matcher = layerBase.matcher(baseLike);
        matcher.matches();
        return Integer.parseInt(baseLike);
    }
}
