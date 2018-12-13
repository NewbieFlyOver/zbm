package wxutils;

import com.alibaba.fastjson.serializer.SerializerFeature;

public class SerializerFeatureUtil {

	public static final SerializerFeature[] FEATURES = {
			SerializerFeature.WriteMapNullValue,
			SerializerFeature.QuoteFieldNames,
			SerializerFeature.WriteNullStringAsEmpty,
			SerializerFeature.WriteNullBooleanAsFalse,
			SerializerFeature.WriteNullListAsEmpty,
			SerializerFeature.WriteNullNumberAsZero,
			SerializerFeature.DisableCircularReferenceDetect,
			SerializerFeature.IgnoreErrorGetter
	};
}
