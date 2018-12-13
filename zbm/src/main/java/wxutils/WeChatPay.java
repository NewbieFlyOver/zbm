package wxutils;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeChatPay{
	public Integer orderId;
	public BigDecimal advancedPaid; //提前宰杀已付费用
	
	public BigDecimal getAdvancedPaid() {
		return advancedPaid;
	}
	public void setAdvancedPaid(BigDecimal advancedPaid) {
		this.advancedPaid = advancedPaid;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
}