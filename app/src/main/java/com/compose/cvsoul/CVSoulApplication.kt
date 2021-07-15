package com.compose.cvsoul

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import com.compose.cvsoul.util.getDefaultOkHttpClient
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.cahce.CacheMode
import java.util.*

class CVSoulApplication: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        // 初始化RxHttp
        val customHttpClient = getDefaultOkHttpClient()
        RxHttpPlugins.init(customHttpClient)
            //是否开启调试模式，开启后，logcat过滤RxHttp，即可看到整个请求流程日志
            .setDebug(true)
            //设置最大缓存为10M，缓存有效时长为24小时
//            .setCache(externalCacheDir, 10 * 1024 * 1024, CacheMode.READ_CACHE_FAILED_REQUEST_NETWORK, 24 * 60 * 60 * 1000)
            //设置一些key，不参与cacheKey的组拼
//            .setExcludeCacheKeys(String...)
            //设置数据解密/解码器
//            .setResultDecoder(Function)
            .setOnParamAssembly {
                // 设置公共请求头
                it
                    .addHeader("version", "1.0")
                    .addHeader("platform", "android")
                    .addHeader("brand", Build.BRAND) // 手机品牌
                    .addHeader("product", Build.PRODUCT) // 手机型号
                    .addHeader("system", Build.VERSION.RELEASE) // 系统版本
                    .addHeader("timestamp", System.currentTimeMillis().toString())
                    .addHeader("nonce", UUID.randomUUID().toString())
            }
    }
}