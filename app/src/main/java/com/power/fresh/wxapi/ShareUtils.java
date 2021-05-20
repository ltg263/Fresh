package com.power.fresh.wxapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.App;
import com.power.fresh.R;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @author AlienChao
 * @date 2020/08/05 09:11
 */
public class ShareUtils {
    /**
     * 分享网页类型至微信
     * type 1 对话 2 朋友圈  3 收藏
     */
    public static void shareWeb(Context context, int type, String webUrl) {
        String title = "宁将生鲜";
        String content = "这里优惠多多，赶紧来吧！";
        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.app_share);
        // 通过appId得到IWXAPI这个对象
        IWXAPI wxapi = WXAPIFactory.createWXAPI(context, App.APP_ID);
        // 检查手机或者模拟器是否安装了微信
        if (!wxapi.isWXAppInstalled()) {
            ToastUtils.show("您还没有安装微信");
            return;
        }

        // 初始化一个WXWebpageObject对象
        WXWebpageObject webpageObject = new WXWebpageObject();
        // 填写网页的url
        webpageObject.webpageUrl = webUrl;

        // 用WXWebpageObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        // 填写网页标题、描述、位图
        msg.title = title;
        msg.description = content;


        // 如果没有位图，可以传null，会显示默认的图片
        msg.setThumbImage(bitmap1);

        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        // transaction用于唯一标识一个请求（可自定义）
        req.transaction = "webpage";
        // 上文的WXMediaMessage对象
        req.message = msg;
        // SendMessageToWX.Req.WXSceneSession是分享到好友会话
        // SendMessageToWX.Req.WXSceneTimeline是分享到朋友圈
        if (type == 2) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else if (type == 3) {
            req.scene =   SendMessageToWX.Req.WXSceneFavorite;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }


        // 向微信发送请求
        wxapi.sendReq(req);
    }
}
