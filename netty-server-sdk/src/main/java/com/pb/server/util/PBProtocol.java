package com.pb.server.util;


import io.netty.buffer.ByteBuf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by piecebook on 16-8-6.
 */
public class PBProtocol {
	public static byte[] Encode(int encode, int enzip, HashMap<String, String> params) {
		String charset = getCharset(encode);
		ByteArrayOutputStream body = new ByteArrayOutputStream();
		Iterator iter = params.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
			try {
				byte[] key_bytes = entry.getKey().getBytes(charset);
				byte[] value_bytes = entry.getValue().getBytes(charset);
				int length = key_bytes.length;
				if(length > 32767)	throw new IOException("Message Too long");
				byte low = (byte) length;
				byte high = (byte) (length >> 8);
				body.write(high);
				body.write(low);
				body.write(key_bytes);
				length = value_bytes.length;
				low = (byte) length;
				high = (byte) (length >> 8);
				body.write(high);
				body.write(low);
				body.write(value_bytes);
			} catch (UnsupportedEncodingException e) {
				//TODO:处理异常
				e.printStackTrace();
			} catch (IOException e) {
				//TODO:处理异常
				e.printStackTrace();
			}
		}
		return body.toByteArray();

	}

	public static HashMap<String, String> Decode(int encode, int enzip, ByteBuf inbuf) throws UnsupportedEncodingException {
		String charset = getCharset(encode);
		HashMap<String, String> params = null;
		if (inbuf != null && inbuf.readableBytes() > 0) {
			int index = 0;
			int readable_length = inbuf.readableBytes();
			params = new HashMap<String, String>();
			while (index < readable_length) {
				short length = inbuf.readShort();
				byte[] field_bytes = new byte[length];
				inbuf.readBytes(field_bytes);
				String key = new String(field_bytes, charset);
				index = index + 2 + length;

				length = inbuf.readShort();
				field_bytes = new byte[length];
				inbuf.readBytes(field_bytes);
				String value = new String(field_bytes, charset);
				params.put(key, value);
				index = index + 2 + length;
			}

		}
		return params;
	}

	private static String getCharset(int encode) {
		//TODO:根据encode返回编码名字
		return "UTF-8";
	}
}
