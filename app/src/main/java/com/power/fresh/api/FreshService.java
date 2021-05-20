package com.power.fresh.api;

import com.chen.concise.Response;
import com.power.common_opensurce.OkHttpInterceptor;
import com.power.common_opensurce.UserInfo;
import com.power.common_opensurce.UserInfoCc;
import com.power.fresh.bean.AddressBean;
import com.power.fresh.bean.AfterBean;
import com.power.fresh.bean.Booth;
import com.power.fresh.bean.BusinessUser;
import com.power.fresh.bean.CommentList;
import com.power.fresh.bean.CommodityDetails;
import com.power.fresh.bean.CommodityList;
import com.power.fresh.bean.MessageBean;
import com.power.fresh.bean.MessageDetails;
import com.power.fresh.bean.RefundGet;
import com.power.fresh.bean.alipay.PayQueryBean;
import com.power.fresh.bean.bussiness.BussinessUserInfo;
import com.power.fresh.bean.bussiness.DeliveryUserInfo;
import com.power.fresh.bean.bussiness.LiuShuiBean;
import com.power.fresh.bean.bussiness.YuE_Bean;
import com.power.fresh.bean.bussiness.ZjglLiuShuiBean;
import com.power.fresh.bean.bussiness.商品管理统计;
import com.power.fresh.bean.bussiness.商家订单统计;
import com.power.fresh.bean.bussiness.待入库;
import com.power.fresh.bean.bussiness.提现查看;
import com.power.fresh.bean.bussiness.选择银行;
import com.power.fresh.bean.bussiness.配送管理;
import com.power.fresh.bean.bussiness.银行卡列表;
import com.power.fresh.bean.commodity.CommodityCategory;
import com.power.fresh.bean.commodity.CommodityInfo;
import com.power.fresh.bean.home.HomeBanner;
import com.power.fresh.bean.home.HomeBooth;
import com.power.fresh.bean.home.HomeGouponBean;
import com.power.fresh.bean.home.HomeMerchant;
import com.power.fresh.bean.home.HomeSeckill;
import com.power.fresh.bean.home.HomeSeckill2;
import com.power.fresh.bean.marki.MarkiOrderBean;
import com.power.fresh.bean.order.CreateOrderBean;
import com.power.fresh.bean.order.OrderDetails;
import com.power.fresh.bean.order.OrderListBean;
import com.power.fresh.bean.reponse.BankReponse;
import com.power.fresh.bean.reponse.BusinessReponse;
import com.power.fresh.bean.reponse.CreateOrder;
import com.power.fresh.bean.reponse.PayOrderReponse;
import com.power.fresh.bean.reponse.UploadReponse;
import com.power.fresh.bean.request.CreateOrderRequest;
import com.power.fresh.bean.request.SaveOrderRequest;
import com.power.fresh.bean.shoppingcar.SaveCartResponse;
import com.power.fresh.bean.shoppingcar.ShoppingCarBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @explain ： 请求分两种格式 。 文档标注了json就用application-json，  其他的就用x-www-form-urlencoded
 *             默认为json  ， 其他类型加 @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
 * @author AlienChao
 * @date 2020/04/24 15:48
 */
public interface FreshService {





    @GET("qr/getBusiness")
    Observable<Response<String>> qrGetBusiness(@Query("businessId") int businessId);


    @GET("user/business/getBusiness")
    Observable<Response<String>> userBusinessGetBusiness();




    /**
     * 1.1.2 账号检测
     * //true表示账号已存在， 不可注册 | false 表示账号不存在，可以注册
     */
    @GET("user/verify/checkUser")
    Observable<Response<Boolean>> checkUser(@Query("loginName") String loginName);


    @GET("user/verify/getVerifyCode")
    Observable<Response<Object>> userVerifyGetVerifyCode(@Query("mobile") String mobile);


    /**
     * 1.1.2 账号检测
     * //true表示账号已存在， 不可注册 | false 表示账号不存在，可以注册
     */

    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/verify/forgetPwd")
    Observable<Response<Integer>> forgetPwd(@Query("username") String username,@Query("verifyCode") String verifyCode,@Query("pwd") String pwd);

    /**
     * 1.1.2 账号检测
     * //true表示账号已存在， 不可注册 | false 表示账号不存在，可以注册
     */

    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/changePwd")
    Observable<Response<Integer>> userChangePwd(@Query("oldPwd") String oldPwd,@Query("newPwd") String newPwd);


    /** 取消 */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/auth/cancel")
    Observable<Response<Object>> userAuthCancel();


    //1.23.5 订单分配
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/business/delivery/order-sending")
    Observable<Response<Object>> userBusinessOeliveryOrderSending(@Query("deliveryId") int deliveryId,@Query("orderId") int orderId);

    //1.3.6 订单送达
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/delivery/order/service")
    Observable<Response<Object>> userDeliveryOrderService(@Query("orderDeliveryId") int orderDeliveryId);


    /**
     * 1.1.3 账号登陆
     */

    @POST("user/verify/signIn")
    Observable<Response<UserInfo>> signIn(@Query("device") String device,@Query("username") String loginName, @Query("password") String password,  @Query("clientType") int clientType, @Query("logPort") int logPort);


    /**
     * 1.1.4 短信登陆
     */
    @POST("user/verify/smsLogin")
    Observable<Response<UserInfo>> smsLogin(@Query("device") String device,@Query("username") String loginName, @Query("verifyCode") String verifyCode);


    /**
     * 1.1.4 短信登陆
     */
    @POST("user/verify/smsLogin")
    Observable<Response<UserInfo>> smsLogin2(@Query("username") String loginName, @Query("verifyCode") String verifyCode, @Body Booth booth);


    /**
     * 1.1.1 注册
     */
    @POST("user/verify/register")
    Observable<Response<UserInfo>> register(@Query("device") String device,@Query("username") String username, @Query("Password") String Password, @Query("verifyCode") String verifyCode, @Query("recommend") String recommend);


    /**
     * 1.11.1 广告列表/轮播图
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("home/ad/list")
    Observable<Response<HomeBanner>> homeAdList(@Query("seat") int seat, @Query("string") String status);

    /**
     * 1.11.2 分类列表
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("home/category/list")
    Observable<Response<HomeBooth>> homeCategoryList(@Query("Status") String Status ,@Query("page") String page,@Query("pageSize") String pageSize);


    /**
     * 1.11.6 当日优惠券
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("home/coupon/list")
    Observable<Response<HomeGouponBean>> homeCouponLis();


    /**
     * 1.11.6 当日优惠券
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("user/coupon/list")
    Observable<Response<HomeGouponBean>> userCouponList(@Query("page") int page,@Query("pageSize") int pageSize);




    /**
     * 1.11.2 分类列表
     * 1秒杀
     * 2限时抢购
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("home/activity/list")
    Observable<Response<HomeSeckill>> homeActivityList(@Query("type") String type, @Query("showNum") int showNum);
    /**
     * 1.11.2 分类列表
     * 1秒杀
     * 2限时抢购
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("home/activity/list")
    Observable<Response<HomeSeckill>> homeActivityList2(@Query("type") String type, @Query("showNum") int showNum,@Query("activityId") int activityId);

    /**
     * 1.11.4 活动商品
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("home/activity/commodity/list")
    Observable<Response<HomeSeckill2>> commodityList(@Query("activityId") String activityId);


    /**
     * 1.11.5 商家展示（搜索）
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("home/business/list")
    Observable<Response<HomeMerchant>> homeBusinessList(@Query("searchName") String searchName,@Query("lng") double lng, @Query("lat") double lat, @Query("showNum") int showNum
            , @Query("browseOrder") boolean browseOrder, @Query("evaluateOrder") boolean evaluateOrder, @Query("saleNumOrder") boolean saleNumOrder);

    /**
     * 1.11.5 商家展示（商家列表）
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("home/business/list")
    Observable<Response<HomeMerchant>> homeBusinessListList(@Query("categoryId") String categoryId,@Query("lng") double lng, @Query("lat") double lat, @Query("showNum") int showNum
            , @Query("browseOrder") boolean browseOrder, @Query("evaluateOrder") boolean evaluateOrder, @Query("saleNumOrder") boolean saleNumOrder);

    /**
     * 1.4.3 商品列表
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("business/list_by_commodity")
    Observable<Response<HomeMerchant>> businessListByCommodity(@Query("categoryId") int categoryId,@Query("commodityName") String commodityName
            ,@Query("page") int page,@Query("pageSize") int pageSize,@Query("lng") double lng, @Query("lat") double lat);


    /**
     * 1.11.5 商家展示（搜索）
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("business/list_by_commodity")
    Observable<Response<HomeMerchant>> businessListByCommodity2(@Query("page") int page,@Query("pageSize") int pageSize,@Query("categoryId") int categoryId,@Query("commodityName") String commodityName,@Query("lng") double lng, @Query("lat") double lat, @Query("showNum") int showNum
            , @Query("browseOrder") boolean browseOrder, @Query("evaluateOrder") boolean evaluateOrder, @Query("saleNumOrder") boolean saleNumOrder);





    /**
     * 1.11.5 商家展示（商家列表）
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("app/business/supply-list")
    Observable<Response<HomeMerchant>> appBusinessSupplyList(@Query("lng") double lng, @Query("lat") double lat, @Query("showNum") int showNum
            , @Query("browseOrder") boolean browseOrder, @Query("evaluateOrder") boolean evaluateOrder, @Query("saleNumOrder") boolean saleNumOrder);




    /**
     * 1.11.5 商家展示 （首页-没有tab）
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("home/business/list")
    Observable<Response<HomeMerchant>> homeBusinessHomeList(@Query("searchName") String searchName,@Query("lng") double lng, @Query("lat") double lat, @Query("showNum") int showNum,@Query("pageSize") int pageSize,@Query("page") int page);

    /**
     * 1.4.3 商品详情
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("commodity/get")
    Observable<Response<CommodityDetails>> commodityGet(@Query("id") int id);

    /**
     * 1.4.3 商品详情
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("user/notice/list")
    Observable<Response<MessageBean>> commodityGet(@Query("page") int page,@Query("pageSize") int pageSize);


    /**
     * 1.22.1 客户列表
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("user/business-user/list")
    Observable<Response<BusinessUser>> userBusinessSserList(@Query("page") int page, @Query("pageSize") int pageSize);




    /**
     * 1.4.3 商品详情
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("user/notice/get")
    Observable<Response<MessageDetails>> userNoticeGet(@Query("noticeId") int noticeId);



    /**
     * 1.2.2 商家认证信息查看
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("user/business/getInfo")
    Observable<Response<BussinessUserInfo>> userBusinessGetInfo();

   /**
     * 1.3.2 查看信息
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("user/delivery/getDelivery")
    Observable<Response<DeliveryUserInfo>> userDeliveryGetDelivery();



    /**
     * 分类Tab
     * 商品详情
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("commodity/appList")
    Observable<Response<CommodityList>> commodityAppList(@Query("cartCheck") Boolean cartCheck, @Query("searchName") String searchName, @Query("categoryId") Integer categoryId, @Query("hotOrder") Boolean hotOrder);





    /**
     * 商品详情
     * priceOrder 0 升序 1 降序
     * 1.12.1 商品展示(新增供应商商品)
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("commodity/appList")
    Observable<Response<CommodityList>> commodityAppList(@Query("priceOrder") String priceOrder, @Query("businessId") String businessId, @Query("categoryId") String categoryId, @Query("searchName") String searchName);


    /**
     * 店铺详情 + 购买商品-多规格
     * @return
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("commodity/appList")
    Observable<Response<CommodityList>> commodityAppList(@Query("cartCheck") Boolean cartCheck, @Query("priceOrder") Integer priceOrder, @Query("businessId") int businessId, @Query("categoryId") Integer categoryId
            , @Query("searchName") String searchName, @Query("hotOrder") Boolean hotOrder, @Query("specialOrder") Boolean specialOrder, @Query("hasHot") Integer hasHot
    );



    /**
     *供应商商品展示
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("commodity/supply-list")
    Observable<Response<CommodityList>> commoditySupplyList( @Query("businessId") int businessId,@Query("priceOrder") String priceOrder,@Query("page") int page,@Query("pageSize") int pageSize);





    /**
     * 1.13.1 商家分类全部展示(至多二级)
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("site/Category/listAll")
    Observable<Response<CommodityCategory>> siteCategoryListAll(@Query("businessId") int businessId, @Query("categoryId") String categoryId, @Query("categoryName") String categoryName);

    /**
     * 1.13.1 商家分类全部展示  首页请求
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("category/listAll")
    Observable<Response<List<CommodityCategory.ListBean>>> siteCategoryListAll();


    /**
     * 1.12.4商家自身商品展示
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("user/business/commodity/business-list")
    Observable<Response<CommodityList>> userBusinessCommodityBusinessList(@Query("tcStatus") int tcStatus,@Query("page") int page,@Query("pageSize") int pageSize);


    /**
     * 1.12.5 供应商自身商品展示
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("user/business/commodity/supply-list")
    Observable<Response<CommodityList>> userBusinessCommoditySupplyList(@Query("tcStatus") int tcStatus,@Query("page") int page,@Query("pageSize") int pageSize);

    /**
     * 1.25.1 商品上下架
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/business/commodity/update-status")
    Observable<Response<Object>> userBusinessCommodityUpdateStatus(@Query("commodityId") int commodityId,@Query("status") int status);



    /**
     * 1.14.2 app商家简易信息
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("app/business/getSimple")
    Observable<Response<CommodityInfo>> appBusinessGetSimple(@Query("businessId") int businessId);

    /**
     * 1.2.5 商家详情(供应商详情)
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("business/getOne")
    Observable<Response<CommodityInfo>> businessGetOne(@Query("id") int id);


    /**
     * 1.18.2 查看评论
     *  siteid 商家id
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("comment/list")
    Observable<Response<CommentList>> commentList(@Query("siteid") int siteid,@Query("page") int page,@Query("PageSzie") int PageSzie);





    /**
     * 1.11.6  购物车查询
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("user/cart/list")
    Observable<Response<ShoppingCarBean>> userCartList(@Query("page") int page, @Query("pageSize") int pageSize);


    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("user/getQr")
    Observable<Response<String>> getQr();


    /**
     * 1.10.3 购物车删除
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @DELETE("user/cart/remove")
    Observable<Response<Object>> userCartRemove(@Query("id") int id);

    /**
     * 1.10.3 删除单商家购物车
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @DELETE("user/cart/remove-business-cart")
    Observable<Response<Object>> removeBusinessCart(@Query("businessId") int businessId);


    /**
     * 1.10.3 购物车修改数量
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/cart/updateNum")
    Observable<Response<String>> userCartUpdateNum(@Query("cartId") int cartId, @Query("num") int num);


    /**
     * 1.10.2 购物车添加
     * 商家id\ 商品id\规格id\数量
     */

    @POST("user/cart/save")
    Observable<Response<SaveCartResponse>> userCartSave(@Query("distributorId") int distributorId, @Query("commodityId") int commodityId, @Query("normsId") int normsId, @Query("num") Integer num);


    /**
     * 1.10.3 购物车修改数量
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("user/address/list")
    Observable<Response<AddressBean>> userAddressList(@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 1.19.2退款商品列表
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("user/refund/list")
    Observable<Response<AfterBean>> userRefundList(@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 1.19.8 商家采购订单退款列表
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("user/business/refund/business-refund-list")
    Observable<Response<AfterBean>> userBusinessRefund(@Query("page") int page, @Query("pageSize") int pageSize);

   /**
     * 1.19.8 供应商订单退款列表
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("user/business/refund/supply-refund-list")
    Observable<Response<AfterBean>> userBusinessSupplyRefund(@Query("page") int page, @Query("pageSize") int pageSize);


    /**
     * 1.19.7 商家用户订单退款列表
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("user/business/refund/user-refund-list")
    Observable<Response<AfterBean>> userBusinessRefundUserRefundList(@Query("page") int page, @Query("pageSize") int pageSize);


  /**
     * 1.19.3 退款详情
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET("user/refund/get")
    Observable<Response<RefundGet>> userRefundGet(@Query("refundId") int refundId);




    /**
     *1.15.5用户地址设置默认
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/address/update_default")
    Observable<Response<String>> userAddressUpdateDefault(@Query("userAddressId") int userAddressId, @Query("defaultStatus") int defaultStatus);



    /**
     * 1.15.6用户地址删除
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @DELETE("user/address/remove")
    Observable<Response<String>> userAddressRemove(@Query("userAddressId") int userAddressId);

    /**
     *1.15.3用户地址添加 districtId	int	double	区域id
     */
    @POST("user/address/save")
    Observable<Response<String>> userAddressSave(@Query("isDefault") int isDefault,@Query("acceptName") String acceptName,@Query("mobile") String mobile,@Query("location") String location,@Query("districtId") int districtId);

    /**
     *1.15.4用户地址修改nt	double	区域id
     */
    @POST("user/address/update")
    Observable<Response<String>> userAddressUpdate(@Query("id") int id,@Query("isDefault") int isDefault,@Query("acceptName") String acceptName,@Query("mobile") String mobile,@Query("location") String location,@Query("districtId") int districtId);


    /**
     *1.17.1 订单预创建
     */
    @POST("user/order/check")
    Observable<Response<CreateOrderBean>> userOrderCheck(@Body CreateOrderRequest createOrderResponse);

    /**
     *1.17.1 订单预创建
     */
    @POST("user/business/order/check")
    Observable<Response<CreateOrderBean>> userBusinessOrderCheck(@Body CreateOrderRequest createOrderResponse);

    /**
     *1.17.2 创建订单
     */
    @POST("user/order/save")
    Observable<Response<CreateOrder>> userOrderSave(@Body SaveOrderRequest saveOrderResponse);

    /**
     *1.17.7 商家订单创建
     */
    @POST("user/business/order/save")
    Observable<Response<CreateOrder>> userBusinessOrderSave(@Body SaveOrderRequest saveOrderResponse);


    /**
     * 1.1.9 修改基本信息
     */
    @POST("user/update/info")
    Observable<Response<Object>> userUpdateInfo(@Query("nickname") String nickname,@Query("portraitUri") String portraitUri
            ,@Query("gender") int gender,@Query("cityId") int cityId,@Query("provinceId") int provinceId,@Query("regionId") int regionId,@Query("birth") String birth);



    /**
     * 1.17.2 创建订单
     */
    @GET("user/order/list")
    Observable<Response<OrderListBean>> userOrderList(@Query("orderStatus") Integer orderStatus, @Query("page") int page, @Query("pageSize") int pageSize );


    /**
     * 1.20.1 用户订单
     */
    @GET("user/business/order/user-order-list")
    Observable<Response<OrderListBean>> userBusinessOrderUserList(@Query("orderStatus") Integer orderStatus, @Query("page") int page, @Query("pageSize") int pageSize );


    /**
     * 1.23.6物流信息
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/business/order/add-express")
    Observable<Response<Object>> userBusinessOrderAddExpress(@Query("orderId") int orderId, @Query("info") String info );






    /**
     *1.20.2 采购订单
     */
    @GET("user/business/order/business-order-list")
    Observable<Response<OrderListBean>> userBusinessOrderBusinessOrderlist();


    /**
     *1.20.2 采购订单
     */
    @GET("user/business/order/business-order-list")
    Observable<Response<OrderListBean>> userBusinessOrderBusinessOrderlist(@Query("orderStatus") Integer orderStatus, @Query("page") int page, @Query("pageSize") int pageSize);

    /**
     *1.20.2 配送员订单列表
     */
    @GET("user/delivery/order/list")
    Observable<Response<MarkiOrderBean>> userDeliveryOrderList(@Query("orderStatus") Integer orderStatus, @Query("page") int page, @Query("pageSize") int pageSize);


    /**
     *1.2.3 余额查询
     */
    @GET("user/business/getBalance")
    Observable<Response<YuE_Bean>> userBusinessGetBalance();

    /**
     *1.28.2 提现查看
     */
    @GET("user/business/cash-out/list")
    Observable<Response<提现查看>> userBusinessDashList(@Query("page")int page,@Query("pageSize")int pageSize);
    /**
     *1.28.2 提现查看
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/business/cash-out/cancel")
    Observable<Response<Object>> businessCashOutCancel(@Query("cashOutId")int cashOutId);


    /**
     * 1.29 入库信息
     */
    @GET("user/business/stock/log/business-list")
    Observable<Response<待入库>> userBusinessStockBusinessList(@Query("page")int page, @Query("pageSize")int pageSize);




    /**
     *1.26.1 流水统计
     */
    @GET("user/business/balance-log/statistics")
    Observable<Response<LiuShuiBean>> userBusinessBalancelogStatistics();


    /**
     *1.26.1 流水
     * 1支出
     * 2收入
     */
    @GET("user/business/balance-log/list")
    Observable<Response<ZjglLiuShuiBean>> userBusinessBalanceList(@Query("page")int page, @Query("pageSize")int pageSite,@Query("type")int type);


    /**
     *1.24.4 商家银行卡列表
     */
    @GET("user/business/bank/list")
    Observable<Response<银行卡列表>> userBusinessBankList(@Query("page")int page, @Query("pageSite")int pageSite);


    /**
     *1.24.4 商家银行卡列表
     */
    @GET("bank/list-all")
    Observable<Response<List<选择银行>>> bankListAll();




    /**
     *1.21.1商家商品统计
     */
    @GET("user/business/statistics/commodities")
    Observable<Response<商品管理统计>> userBusinessStatisticsCommodities();


    /**
     *1.21.1商家商品统计
     */
    @GET("user/business/bank/get-default")
    Observable<Response<银行卡列表.ListBean>> userBusinessBankDefault();




    /**
     * 1.23.3 旗下配送员
     */
    @GET("user/business/delivery/list")
    Observable<Response<配送管理>> businessDeliveryList(@Query("page") int page, @Query("pageSize") int pageSize );
    /**
     * 1.23.1 配送员查询
     */
    @GET("delivery/search")
    Observable<Response<配送管理>> deliverySearch(@Query("mobile") String mobile);


    /**
     * 1.23.2 配送员添加
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/business/delivery/save")
    Observable<Response<Object>> userBusinessDeliverySave(@Query("deliveryId") int deliveryId);


    /**
     * 1.23.4 配送员删除
     */
    @DELETE("user/business/delivery/remove")
    Observable<Response<Object>> userBusinessDeliveryRemove(@Query("siteDeliveryId") int siteDeliveryId);

    /**
     * 1.24.3 银行卡删除
     */
    @DELETE("user/business/bank/remove")
    Observable<Response<Object>> userBusinessBankRemove(@Query("businessBankId") int businessBankId);






    /**
     *1.17.9 订单删除
     */
    @DELETE("user/order/remove")
    Observable<Response<Object>> userOrderRemove(@Query("orderId") int orderId);

    /**
     *1.17.5 取消订单
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/order/cancel")
    Observable<Response<Object>> userOrderCancel(@Query("orderId") int orderId);

    /**
     *1.28.1 提现操作
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/business/cash-out/create")
    Observable<Response<Object>> businessCashoutCreate(@Query("bankId") int bankId,@Query("amount") double amount);




    /**
     *1.7.1 优惠券领取
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/coupon/save")
    Observable<Response<Object>> userCouponSave(@Query("couponIds") ArrayList<Integer> couponIds);

    /**
     *1.17.10 订单支付
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/pay/order")
    Observable<Response<PayOrderReponse>> userPayOrder(@Query("orderId") int orderId, @Query("orderType") int orderType, @Query("payType") int payType, @Query("subOrderType") String subOrderType);

    /**
     *1.17.10 订单支付
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/pay/order")
    Observable<Response<PayOrderReponse>> userPayOrder退款(@Query("orderId") int orderId, @Query("orderType") int orderType, @Query("payType") int payType, @Query("subOrderType") String subOrderType);



    /**
     *1.19.1 申请退款
     */

    @POST("user/refund/save")
    Observable<Response<PayOrderReponse>> userRefundSave(@Query("orderDetailsId") int orderDetailsId, @Query("num") int num, @Query("remark") String remark, @Query("reason") String reason, @Query("images") String images, @Query("refundType") int refundType);






    /**
     * 1.18.1 添加评论
     */

    @POST("user/comment/save")
    Observable<Response<Object>> userCommentSave(@Query("siteid") int siteid,@Query("orderId") int orderId,@Query("score") int score,@Query("content") String content,@Query("imgUrl") String imgUrl);





    /**
     *1.27.1 意见反馈添加
     */
    @POST("user/feedback/save")
    Observable<Response<Object>> userFeedbackSsave(@Query("mobile") String mobile, @Query("message") String message);


    /**
     *1.17.11 订单查询
     */
    @GET("user/pay/query")
    Observable<Response<PayQueryBean>> userPayQuery(@Query("orderId") int orderId);




    /**
     *1.17.4 订单详情
     */
    @GET("order/getDetails")
    Observable<Response<OrderDetails>> orderGetDetails(@Query("orderId") int orderId);

    /**
     *1.2.1 商家认证
     */
    @POST("user/business/saveOrUpdate")
    Observable<Response<Object>> userBusinessSaveOrUpdate(@Body BusinessReponse businessReponse);

    /**
     *1.3.1 配送员认证
     */
    @POST("user/delivery/saveOrUpdate")
    Observable<Response<Object>> userDeliverySaveOrUpdate(@Query("id") Integer id,@Query("infoSubmitType") int infoSubmitType,@Query("name") String name,@Query("mobile") String mobile,@Query("cardUrl") String cardUrl,@Query("cardUrlBack") String cardUrlBack);


   /**
     *1.17.4 订单详情
     */
    @GET("admin/activity/list")
    Observable<Response<OrderDetails>> adminActivityList(@Query("orderId") int orderId);

   /**
     *1.29.2 手动入库
     */
    @GET("user/business/stock/log/insert-data")
    Observable<Response<Object>> userBusinessStockData(@Query("stockLogNo") String stockLogNo);


   /**
     *1.17.4 订单详情
     */
    @GET("user/getUserInfo")
    Observable<Response<UserInfoCc>> userGetUserInfo();


   /**
     *1.21.2商家订单统计
     */
    @GET("user/business/statistics/orders")
    Observable<Response<商家订单统计>> userBusinessStatisticsOrders();



    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @GET
    Observable<Response<Object>> qrUrl(@Url String url);



    /**
     *1.1.7 退出
     */
    @POST("user/signOut")
    Observable<Response<Object>> userSignOut();

    /**
     *1.24.1 银行卡添加
     */
    @POST("user/business/bank/save")
    Observable<Response<Object>> userBusinessBankSave(@Body BankReponse bankReponse);



    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("files")
    Observable<Response<UploadReponse>> upLoad(@Body RequestBody Body);







}
