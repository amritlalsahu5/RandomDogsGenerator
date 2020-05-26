package com.frankymedia.dogsgenerator.glide

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule


@GlideModule
/*
       Custom Glide module for configuration of Cache
       For Memory Caching: LruResoureCache is used.
       For DiskCache : Lru Caching is used by default
       memoryCacheSizeBytes : is the cached size allowed for now.
       Moreover requestOptions method can also be overriden to
       provide more options to the GlideModule or else can be set
       directly while creating the GlideApp objects as well.

 */
class DogsGeneratorGlideModule : AppGlideModule() {
    lateinit var context :Context
    val noOfImagesForCaching : Int = 20
    override fun applyOptions(
        context: Context,
        builder: GlideBuilder
    ) {
        this.context = context
        val memoryCacheSizeBytes = 1024 * 1024 * noOfImagesForCaching // 20mb
        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes.toLong()))
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, memoryCacheSizeBytes.toLong()))

    }

}