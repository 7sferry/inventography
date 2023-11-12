/************************
 * Made by [MR Ferry™]  *
 * on October 2023      *
 ************************/

package org.example.utils;

import de.huxhorn.sulky.ulid.ULID.Value;

import java.util.Optional;

public final class ULIDS{
	private ULIDS(){
		throw new UnsupportedOperationException("can't be instantiated");
	}

	public static String nextULID(){
		return Holder.ULID.nextULID();
	}

	public static String nextULID(long timestamp){
		return Holder.ULID.nextULID(timestamp);
	}

	public static Optional<Value> nextStrictlyMonotonicValue(Value value){
		return Holder.ULID.nextStrictlyMonotonicValue(value);
	}

	public static Value nextValue(){
		return Holder.ULID.nextValue();
	}

	public static Value nextMonotonicValue(Value value){
		return Holder.ULID.nextMonotonicValue(value);
	}

	private static class Holder{
		private static final de.huxhorn.sulky.ulid.ULID ULID = new de.huxhorn.sulky.ulid.ULID();
	}
}
