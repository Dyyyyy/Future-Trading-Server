package com.wave.controller;

import com.wave.cache.TradeCache;
import com.wave.model.ContractItem;
import com.wave.model.TradeRecord;
import com.wave.model.User;
import com.wave.model.UserContract;
import com.wave.repository.EntityRepository.ContractItemRepository;
import com.wave.repository.EntityRepository.TradeRecordRepository;
import com.wave.repository.EntityRepository.UserContractRepository;
import com.wave.repository.EntityRepository.UserRepository;
import com.wave.staticsetting.ReturnStatus;
import com.wave.staticsetting.StaticConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;




@RestController
public class UserController {
    @Autowired
    private UserRepository user_repository;
    

    @Autowired
    private ContractItemRepository contractItem_repository;
    @Autowired
    private HttpServletRequest Request;
    @Autowired
    private TradeRecordRepository tradeRecordRepository;
    @Autowired
    private UserContractRepository userContractRepository;
    @Autowired
    private TradeCache tradeCache;

    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/user/login")
    public HashMap<String,Integer> login() {
        return ReturnStatus.getReturn(ReturnStatus.LOGIN_PASSERROR);
    }

    @RequestMapping(value = "/user/login_fail")
    public HashMap<String, Integer> login_fail(){
        return ReturnStatus.getReturn(ReturnStatus.LOGIN_PASSERROR);
    }

    @RequestMapping(value = "/user/login_success")
    public HashMap<String,Integer> login_success(){
        return ReturnStatus.getReturn(ReturnStatus.LOGIN_SUCCESS);
    }

    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    public HashMap<String,Integer> register(@RequestParam(value = "name")String name,
                                            @RequestParam(value = "first_name")String f_n,
                                            @RequestParam(value = "last_name")String l_n,
                                            @RequestParam(value = "password")String password,
                                            @RequestParam(value = "age",defaultValue = "0")int age,
                                            @RequestParam(value = "sex",defaultValue = "0")int sex,
                                            @RequestParam(value = "phone_number")String p_n,
                                            @RequestParam(value = "email")String email){

        password=new BCryptPasswordEncoder().encode(password);
        System.out.println(password.length());

        User user=new User();
        user.setNickname(name);
        user.setFirst_name(f_n);
        user.setLast_name(l_n);
        user.setPassword(password);
        user.setAge(age);
        user.setPhone_number(p_n);
        user.setEmail(email);
        user.setSex(sex);
        user.setPortrait_url(StaticConfig.DEFAULT_PORTRAIT_URL);
        user.setEnabled(true);
        user.setTotalProfile(0);
        user.setDepist((float) 0.01);
        
        user.setAccount_balance(50000);
        
        try{
            user_repository.save(user);
            return ReturnStatus.getReturn(ReturnStatus.REGISTER_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ReturnStatus.getReturn(ReturnStatus.REGISTER_FAIL);
        }
    }

    @RequestMapping(value = "/user/logintest")
    public String logintest(){
        return "login_success";
    }

    @RequestMapping(value = "/user/logout")
    public void logout(){}

    @RequestMapping(value = "/user/logout_success")
    public HashMap<String,Integer> logout_success(){
        return ReturnStatus.getReturn(ReturnStatus.LOGOUT_SUCCESS);
    }

    @RequestMapping(value = "/user/test")
    public HashMap<String, Integer> test() {
        return ReturnStatus.getReturn(ReturnStatus.LOGIN_USER_NONEXIST);
    }
    
    @RequestMapping(value = "user/sell", method = RequestMethod.POST)
    public HashMap<String,Object> UserSell(@RequestParam(value = "name")String name,
    					  					@RequestParam(value = "price")Float price,
    					  					 @RequestParam(value = "hands")Float hands,
    					  					 @RequestParam(value = "phone_number")String phone_number){
//    					  					 @RequestParam HttpServletRequest request){
    		
    	//判断余额是否足够 更新交易列表 用户余额 持仓列表，返回当前所有持仓信息
    	Date date = new Date();
    		
    	//获取用户信息
    	User user = new User();
//    	String phone_number=request.getRemoteUser();
    	user = user_repository.findByPhoneNumber(phone_number);
    		
    	//卖出价格
    	float account_balance = user.getAccount_balance() + hands * price;
    		
    	//获取合约信息
    	ContractItem contractItem = new ContractItem();
    	contractItem = contractItem_repository.findBysecShortName(name);
    		
    	//更新持仓列表
    	UserContract userContract = new UserContract();
    	userContract.setUser(user);
    	userContract.setAmount(hands);
    	userContract.setOpen_offset(0);
    	userContract.setPrice(price);
    	userContract.setDir(0);
    	userContract.setContract_item(contractItem);
    	userContractRepository.save(userContract);
    		
    	//更新交易列表
    	TradeRecord tradeRecord = new TradeRecord();
    	tradeRecord.setUser(user);
    	tradeRecord.setAmount(hands);
    	tradeRecord.setDate(date);
    	tradeRecord.setOpen_offset(0);	//0表示开仓
    	tradeRecord.setPrice(price);
    	tradeRecord.setTrading_type(0);	//0表示市价
    	tradeRecord.setContract_item(contractItem);
    	tradeRecord.setType(0);	//0表示卖出
    	tradeRecord.sethandsId(userContract.getId());
    	tradeRecordRepository.save(tradeRecord);
    	
    	//更新用户信息
    	user.setAccount_balance(account_balance);
    	user_repository.save(user);
    	//返回消息
    	HashMap<String, Object> result = new HashMap<String, Object>();
    	result.put("name", name);
    	result.put("success", true);
    	result.put("msg", "success");
    	result.put("time", date);
    	result.put("order", userContract.getId());
    	return result;
    	
    }

    @RequestMapping(value = "/user/buy")
    public HashMap<String, Object> UserBuy(@RequestParam(value = "name")String name,
											@RequestParam(value = "price")Float price,
											@RequestParam(value = "hands")Float hands,
											@RequestParam(value = "phone_number")String phone_number){
    	HashMap<String, Object> result = new HashMap<>();
    	result.put("name", name);
    	User user = new User();
    	Date date = new Date();
    	user = user_repository.findByPhoneNumber(phone_number);
    	if(user.getAccount_balance() - price * hands < 0) {
    		result.put("msg", "余额不足");
    		result.put("success", true);
        	result.put("time", date);
    		return result;
    	}
    	//买进价格
		float account_balance = user.getAccount_balance() - hands * price;
		
		//获取合约信息
		ContractItem contractItem = new ContractItem();
		contractItem = contractItem_repository.findBysecShortName(name);

		//更新持仓列表
		UserContract userContract = new UserContract();
		userContract.setUser(user);
		userContract.setAmount(hands);
		userContract.setOpen_offset(0);
		userContract.setDir(1);
		userContract.setPrice(price);
		userContract.setContract_item(contractItem);
		userContractRepository.save(userContract);
		
		//更新交易列表
    	TradeRecord tradeRecord = new TradeRecord();
    	tradeRecord.setUser(user);
    	tradeRecord.setAmount(hands);
    	tradeRecord.setDate(date);
    	tradeRecord.setOpen_offset(0);	//0表示开仓
    	tradeRecord.setPrice(price);
    	tradeRecord.setTrading_type(0);	//0表示市价
    	tradeRecord.setContract_item(contractItem);
    	tradeRecord.setType(0);	//0表示卖出
    	tradeRecord.sethandsId(userContract.getId());
    	tradeRecordRepository.save(tradeRecord);
		
		//更新用户信息
		user.setAccount_balance(account_balance);
		user_repository.save(user);
		result.put("success", true);
    	result.put("msg", "success");
    	result.put("time", date);
    	result.put("order", userContract.getId());

    	return result;
    }
    
    @RequestMapping(value = "user/handsrefresh")
    public ArrayList<HashMap<String, Object>> HandsRefresh(@RequestParam(value = "phone_number")String phone_number) {
//    											@RequestParam HttpServletRequest request) {
    	User user = new User();
    	user = user_repository.findByPhoneNumber(phone_number);
    	
    	List<UserContract> userContracts = userContractRepository.findByUser(user);
    	
    	ArrayList<HashMap<String, Object>> results = new ArrayList<>();
    	for (UserContract userContract : userContracts) {
    		if(userContract.getOpen_offset() == 1)
    			continue;
    		HashMap<String, Object> result = new HashMap<>();
    		ContractItem contractItem =  userContract.getContract_item();
    		float openPrice = userContract.getPrice();
    		float closePrice = tradeCache.getContract(contractItem.getTicker()).getInfo().getLatest_price();
    		result.put("name", contractItem.getSecShortName());
    		result.put("hands", userContract.getAmount());
			if(userContract.getDir() == 0)
				result.put("dir", "卖出");
			else
				result.put("dir", "买进");
			result.put("openprice", openPrice);
			result.put("closeprice",closePrice);
			result.put("stopLoss", openPrice * 0.1);
			result.put("winLoss", openPrice * 0.3);
			result.put("rebate", (openPrice - closePrice) * 10000);
			result.put("interest", 1);
			result.put("profit", openPrice - closePrice);
			result.put("time", userContract.getDate());
			result.put("order", userContract.getId());
			results.add(result);
		}
    	return results;
    }
    
    @RequestMapping(value = "/user/accountrefresh")
    public HashMap<String, Object> AccountRefresh(@RequestParam(value = "phone_number")String phone_number) {
    	User user = new User();
    	user = user_repository.findByPhoneNumber(phone_number);
    	
    	HashMap<String, Object> result = new HashMap<>();
    	result.put("account", user.getNickname());
    	result.put("balance", user.getAccount_balance());
    	List<UserContract> userContracts = userContractRepository.findByUser(user);
    	float netWorth = user.getAccount_balance();
    	float marginLevel = user.getDepist();
    	float occupyMargin = 0;
    	for(UserContract userContract : userContracts) {
    		if(userContract.getDir() == 0)
    			continue;
    		occupyMargin += marginLevel * userContract.getPrice() * userContract.getAmount();
    		if (userContract.getDir() == 0)
				netWorth -= userContract.getPrice();
    		else
    			netWorth += userContract.getPrice();
    	}
    	result.put("netWorth", netWorth);
    	if(netWorth < 0)
    		result.put("netWorhColor", "red");
    	else
    		result.put("netWorhColor", "green");
    	float handsMargin = user.getDepist() * user.getAccount_balance();
    	result.put("handsMargin", handsMargin);
    	result.put("occupyMargin", occupyMargin);
    	result.put("usableBalance", handsMargin - occupyMargin);
    	result.put("totalProfile", user.getTotalProfile());
    	result.put("marginLevel", marginLevel);
    	return result;
    }
    
    @RequestMapping(value = "/user/offhands")
    public HashMap<String, Object> OffHands(@RequestParam(value = "order") long order, //持仓单号
    										@RequestParam(value = "price") float price,
    										@RequestParam(value = "phone_number") String phone_number) {
    	HashMap<String, Object> result = new HashMap<>();
    	Date date = new Date();
    	User user = new User();
    	user = user_repository.findByPhoneNumber(phone_number);
    	UserContract userContract = new UserContract();
    	userContract = userContractRepository.findById(order);
//    	List<TradeRecord> oleTradeRecord;
//    	oleTradeRecord = tradeRecordRepository.findByHandsId(order);
    	TradeRecord tradeRecord = new TradeRecord();
    	tradeRecord.setPrice(price * userContract.getAmount());
    	tradeRecord.setAmount(userContract.getAmount());
    	tradeRecord.sethandsId(order);
    	tradeRecord.setContract_item(userContract.getContract_item());
    	tradeRecord.setDate(date);
    	tradeRecord.setUser(user);
    	tradeRecord.setOpen_offset(1);
    	float account_balance = user.getAccount_balance();
    	if(userContract.getDir() == 0) {
    		tradeRecord.setTrading_type(1);
    		if(account_balance - tradeRecord.getPrice() < 0) {
    			result.put("success", false);
    	    	result.put("msg", "余额不足");
    	    	result.put("time", date);
    	    	return result;
    		}
    			
    		user.setAccount_balance(account_balance - tradeRecord.getPrice());
    	}
    	else {
    		tradeRecord.setTrading_type(0); 
    		user.setAccount_balance(account_balance - tradeRecord.getPrice());
    	}
    	tradeRecordRepository.save(tradeRecord);
    	userContract.setOpen_offset(1);
    	userContractRepository.save(userContract);
    	result.put("success", true);
    	result.put("msg", "success");
    	result.put("time", date);
    	return result;
    }

    
    @RequestMapping(value = "/user/donerefresh")
    public List<HashMap<String, Object>> DoneRefresh(@RequestParam(value = "phone_number") String phone_number) {
    	ArrayList<HashMap<String, Object>> results = new ArrayList<HashMap<String,Object>>();
    	User user = new User();
    	user = user_repository.findByPhoneNumber(phone_number);
    	List<TradeRecord> tradeRecords = tradeRecordRepository.findByUser(user);
    	for(TradeRecord tradeRecord : tradeRecords) {
    		HashMap<String, Object> result = new HashMap<>();
    		result.put("doneID", tradeRecord.getId());
    		result.put("orderID", tradeRecord.gethandsId());
    		result.put("tradeType", "市价");
    		if(tradeRecord.getOpen_offset() == 0)
    			result.put("onType", "开");
    		else
    			result.put("onType", "平");
    		if(tradeRecord.getType() == 0)
    			result.put("dir", "卖出");
    		else
    			result.put("dir", "买进");
    		result.put("hands", tradeRecord.getAmount());
    		result.put("rebate", 1);
    		result.put("donePrice", tradeRecord.getPrice());
    		result.put("time", tradeRecord.getDate());
    		result.put("note", "note");
    		results.add(result);
    	}
    	return results;
    }
    @RequestMapping(value = "/user/profilerefresh")
    public List<HashMap<String, Object>> ProfileRefresh(@RequestParam(value = "phone_number") String phone_number) {
    	ArrayList<HashMap<String, Object>> results = new ArrayList<HashMap<String,Object>>();
    	User user = new User();
    	user = user_repository.findByPhoneNumber(phone_number);
    	ArrayList<UserContract> userContracts = userContractRepository.findByUser(user);
    	for(UserContract userContract : userContracts) {
    		if(userContract.getOpen_offset() == 0)
    			continue;
    		HashMap<String, Object> result = new HashMap<>();
    		ArrayList<TradeRecord> tradeRecords = tradeRecordRepository.findByHandsId(userContract.getId());
    		result.put("name", contractItem_repository.findById(userContract.getContract_item().getId()).getSecShortName());
    		result.put("dir", userContract.getDir());
    		result.put("hands", userContract.getAmount());
    		float openPrice = tradeRecords.get(0).getPrice();
    		float closePrice = tradeRecords.get(1).getPrice();
    		result.put("openPrice", openPrice);
    		result.put("closePrice", closePrice);
    		result.put("interest", openPrice - closePrice);
    		result.put("rebate", (openPrice - closePrice) * 10000);
    		result.put("doneID", tradeRecords.get(1).getId());
    		result.put("handsID", userContract.getId());
    		result.put("openTime", tradeRecords.get(0).getDate());
    		result.put("doneTime", tradeRecords.get(1).getDate());
    		results.add(result);
    	}
    	return results;
    }

    @RequestMapping(value = "/user/get_user")
    public User getUser(){
        return user_repository.findByPhoneNumber(request.getRemoteUser());
    }
}
