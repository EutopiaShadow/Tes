package com.eutopia.shadow.everyday.grouping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.eutopia.shadow.everyday.grouping.DTO.HxAccountTradeDTO;
import com.eutopia.shadow.everyday.grouping.DTO.YbAccountTradeDTO;
import com.eutopia.shadow.everyday.grouping.domain.HxAccountTrade;
import com.eutopia.shadow.everyday.grouping.domain.YbAccountTrade;
import com.eutopia.shadow.everyday.grouping.param.AccountTradeParamDTO;

/**
 * 华兴的永远放在最后面
 * 
 * 查询宜宾流水
 * 先查询华兴是否开过户,没开过户就不可能有华兴流水
 * 若开过户,查询华兴流水(传参查询所有)
 * 当前页宜宾数等于10,不做处理
 * 当前宜宾页数小于10,需要处理华兴流水
 * 1.宜宾当前页+华兴总数<=10 直接填充并返回
 * 2.宜宾当前页+华兴总数>10 溢出的为华兴流水,页数增加
 * 若点击的页数为越界数字,代表查询的是溢出的华兴流水
 * 思路不对...
 * 
 * 华兴提现也应入datura库,增加唯一标识
 * 
 */
public class GroupUtils {
	
	//一页为10条
	final int pageSize = 10;
	
	YbAccountTradeDTO group(AccountTradeParamDTO demoDto) {
		
		
		
		//demo 假设为查询宜宾返回的结果
		YbAccountTradeDTO ybdto = new YbAccountTradeDTO();
		
		//若未开户,直接返回
		if (!isOpenHxAccount(111)) {
			return ybdto; 
		}
		
		//若开户,必查华兴流水
		//demo 假设为查询华兴返回的结果(传参为查询所有)
		HxAccountTradeDTO hxdto = new HxAccountTradeDTO();
		List<HxAccountTrade> hxAccountTrades = hxdto.getContent();
		
		// 必查宜宾流水
		List<YbAccountTrade> ybAccountTrades = ybdto.getContent();
		
		//一页皆为宜宾不做处理
		if (ybAccountTrades.size() == pageSize) {
			return ybdto;
		}
		
		//宜宾当前页+华兴总数<=10 直接填充并返回
		if (ybAccountTrades.size() + hxAccountTrades.size() <= pageSize) {
			YbAccountTrade ybAccountTrade = null;
			for (int i = 0, l = hxAccountTrades.size(); i < l; i++) {
				ybAccountTrade = new YbAccountTrade();
				BeanUtils.copyProperties(hxAccountTrades.get(i), ybAccountTrade);
				ybAccountTrades.add(ybAccountTrade);
			}
			ybdto.setContent(ybAccountTrades);
			return ybdto;
		}
		
		//宜宾当前页+华兴总数>10 溢出的为华兴流水,页数加1,保证页数正确
		//ybdto.setTotalPageNumber(ybdto.getTotalPageNumber() + 1);
		if (ybAccountTrades.size() + hxAccountTrades.size() > 10) {
			// TODO
			
		}
		
		
		return null;
	}
	
	/**
	 * 查询华兴流水
	 * @param size
	 * @return
	 */
	List<HxAccountTrade> queryHxAccountList(int size) {
		HxAccountTrade hxAccountTrade = null;
		List<HxAccountTrade> hxAccountTrades = new ArrayList<HxAccountTrade>();
		for (int i = 0; i < size; i++) {
			hxAccountTrade = new HxAccountTrade();
			hxAccountTrade.setContent("hx " + i);
			hxAccountTrades.add(hxAccountTrade);
		}
		return hxAccountTrades;
	}
	
	/**
	 * 查询宜宾流水
	 * @param size
	 * @return
	 */
	List<YbAccountTrade> queryYbAccountList(int size) {
		YbAccountTrade ybAccountTrade = null;
		List<YbAccountTrade> ybAccountTrades = new ArrayList<YbAccountTrade>();
		for (int i = 0; i < size; i++) {
			ybAccountTrade = new YbAccountTrade();
			ybAccountTrade.setContent("hx " + i);
			ybAccountTrades.add(ybAccountTrade);
		}
		return ybAccountTrades;
	}
	
	
	/**
	 * 判断华兴是否开过户
	 * @param userId
	 * @return
	 */
	boolean isOpenHxAccount(long userId) {
		// always true...
		return true;
	}
}
