package wmq.fly.utils;

import javax.servlet.http.HttpServletRequest;

public class GetRealPath {
	
	
	public static void main(String[] args) {
		
	}
	
	public void getRealPath(HttpServletRequest request) {
		
		//保存的服务器绝对地址
		String path = request.getSession().getServletContext().getRealPath("/BackstageShoppingWebsite/images/addCircleimage");
		//输出：C:\ww\workSpace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\huiyangche\BackstageShoppingWebsite\images\addCircleimage
	
		//保存的服务器相对地址
		String path1 = request.getContextPath();
		//输出：\huiyangche
	}

}
