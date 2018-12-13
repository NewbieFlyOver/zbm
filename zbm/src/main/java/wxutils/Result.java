package wxutils;

import java.io.Serializable;

public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer code;
	
	public Object data;
	
	public String msg;

	public Result(Integer code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public Result(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public static Result ok(){
		return new Result(200, "SUCCESS");
	}
	
	public static Result ok(String msg){
		return new Result(200, msg);
	}
	
	public static Result ok(Object data){
		return new Result(200, "SUCCESS", data);
	}
	
	public static Result ok(Integer code, String msg, Object data){
		return new Result(code, msg, data);
	}
	
	public static Result error(){
		return new Result(-1, "FAILED");
	}
	
	public static Result error(String msg){
		return new Result(-1, msg);
	}
	
	public static Result error(Integer code, String msg){
		return new Result(code, msg);
	}
	
}
