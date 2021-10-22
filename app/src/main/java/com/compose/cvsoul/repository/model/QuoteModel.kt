package com.compose.cvsoul.repository.model

/**
 * 角色信息
 * @param name 姓名
 * @param avatar 头像
 * @param cv 声优
 */
data class CharacterModel(val name: String, val avatar: String, val cv: String)

/**
 * 音频信息
 * @param _id
 * @param url 网络链接
 * @param character 角色 CharacterModel
 * @param text 文本内容
 * @param pronounce 发音
 * @param translate 翻译
 * @see CharacterModel
 */
data class VoiceModel(val _id: String, val character: CharacterModel, val url: String, val text: String, val pronounce: String, val translate: String)

/**
 * 语录封面显示
 * @param _id
 * @param cover 封面
 * @param title 标题
 */
data class QuoteAlbumDisplayModel(val _id: String, val cover: String, val title: String)

/**
 * 语录集
 * @param _id
 * @param cateId 所属分类
 * @param user 创建者
 * @param cover 封面
 * @param title 标题
 * @param desc 描述
 * @param tags 标签
 * @param voices 语录列表 List<VoiceModel?>
*  @see VoiceModel
 */
data class QuoteAlbumPlaylistModel(val _id: String, val cateId: String, val user: ProfileModel, val cover: String, val title: String, val desc: String, val tags: List<String?>, val voices: List<VoiceModel?>)