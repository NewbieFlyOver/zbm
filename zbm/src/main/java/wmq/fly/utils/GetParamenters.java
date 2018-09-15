package wmq.fly.utils;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 取post、get方法传过来url中的json格式的参数并实例化为对象
 *
 */

@RestController
public class GetParamenters {
	
	//用户存放要实例化的类名及类型
	public static final HashMap<String, Class> SC_MSG_MAP = new HashMap<String, Class>();

    // supported JSON object
    static {
        /*SC_MSG_MAP.put(PollQuery.class.getName(), PollQuery.class);
        SC_MSG_MAP.put(PollData.class.getName(), PollData.class);
        SC_MSG_MAP.put(ControlMsg.class.getName(), ControlMsg.class);
        SC_MSG_MAP.put(LogonMsg.class.getName(), LogonMsg.class);
        SC_MSG_MAP.put(OrderMsg.class.getName(), OrderMsg.class);
        SC_MSG_MAP.put(AdvertisementRequest.class.getName(), AdvertisementRequest.class);*/
        }
	
	
	@RequestMapping(value="/getParamenters")
	public void getParamenters(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		if (true) {
            Map map = new HashMap();
            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();

                String[] paramValues = request.getParameterValues(paramName);
                if (paramValues.length == 1) {
                    String paramValue = paramValues[0];
                    if (paramValue.length() != 0) {
                        map.put(paramName, paramValue);
                    }
                }
            }

            Set<Map.Entry<String, String>> set = map.entrySet();
            //System.out.println("------------------------------");
            for (Map.Entry entry : set) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
            //System.out.println("------------------------------");
            request.setCharacterEncoding("UTF-8");
            //json格式的数据
            String inputObjectStr = request.getParameter("param1");
            //将json转化为对应的类名全路径
            String classTagStr = request.getParameter("param2");
            /*            if (inputObjectStr != null) {
                //Logger.log("server side receiving<<<\r\n" + inputObjectStr, Logger.LEVEL.ERROR);
              //  ScCore.instance().initTimers();
               //将json转化为对象
               // Object resObj = ScCore.instance().handlReqObject(parseInputJsonStr(inputObjectStr, classTagStr));

                if (resObj != null) {
                    sendScResponse(resObj, response);
                } else {
                    Logger.log("failed processing", Logger.LEVEL.ERROR);
                }
            } else {
                ScCore.instance(); // init everything
                Logger.log("No inputObjectStr", Logger.LEVEL.ERROR);
            }
        } else {
            System.out.println("Insecure request from " + request.getRemoteAddr());

        }*/
	}
	
	}
}
