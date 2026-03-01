# Kotlin MultiType

[English](README.md) | 中文

一款强大而灵活的 Kotlin 多类型 RecyclerView 适配器库。

## 特性

- ✅ 支持多种 item 类型
- ✅ 类型安全的 item 绑定
- ✅ 简单的注册机制
- ✅ 支持异步列表差异计算
- ✅ 丰富的辅助工具类
- ✅ 完全 Kotlin 实现

## 引入依赖

```kotlin
// build.gradle.kts
implementation("com.multitype:core:1.0.0")
```

## 快速开始

### 1. 定义 ItemBinder

```kotlin
class TextItemBinder : ItemBinder<TextItem, TextItemBinder.TextViewHolder>() {

    override fun onBindViewHolder(holder: TextViewHolder, item: TextItem) {
        holder.textView.text = item.text
    }

    override fun onCreateViewHolder(parent: ViewGroup): TextViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_text, parent, false)
        return TextViewHolder(view)
    }

    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}

data class TextItem(val text: String)
```

### 2. 注册到适配器

```kotlin
val adapter = MultiTypeAdapter()
adapter.register(TextItemBinder())
```

### 3. 设置数据

```kotlin
adapter.items = listOf(
    TextItem("Hello"),
    TextItem("World")
)

recyclerView.adapter = adapter
```

## 多类型支持

```kotlin
// 注册多种类型的 ItemBinder
adapter.register(TextItemBinder())
adapter.register(ImageItemBinder())
adapter.register(VideoItemBinder())

// 设置混合数据
adapter.items = listOf(
    TextItem("标题"),
    ImageItem("https://..."),
    TextItem("描述"),
    VideoItem("video_url")
)
```

## 异步列表差异计算

使用 `AsyncListDiffer` 高效更新列表：

```kotlin
val differ = AsyncListDiffer(ItemCallback { oldItem, newItem -> 
    oldItem == newItem 
})

differ.submitList(newList)
```

## 辅助工具类

库提供了丰富的辅助工具类，方便日常开发：

| 类名 | 说明 |
|------|------|
| `EmptyViewHandler` | 处理空列表状态 |
| `HeaderFooterHelper` | 管理头尾视图 |
| `ItemAnimator` | item 动画 |
| `ItemClickListener` | 点击事件处理 |
| `ItemFilter` | 列表过滤 |
| `ItemSorter` | 列表排序 |
| `ItemGrouper` | 列表分组 |
| `ItemSearcher` | 列表搜索 |
| `ItemSelector` | 多选支持 |
| `ItemCache` | 缓存管理 |
| `ItemValidator` | 数据验证 |
| `ItemTransformer` | 数据转换 |
| `Paginator` | 分页加载 |
| `ItemLoader` | 数据加载 |

## 架构

```
com.multitype.core
├── MultiTypeAdapter.kt      # 主适配器
├── ItemBinder.kt           # item 绑定器
├── MultiTypePool.kt        # 类型池
├── SafeMultiTypeAdapter.kt # 安全适配器
├── AsyncListDiffer.kt      # 异步差异计算
├── ItemCallback.kt         # item 回调
├── ItemEventDispatcher.kt  # 事件分发
└── [更多辅助类...]
```

## 示例

查看 [sample](sample) 模块获取完整示例。

## License

```
MIT License
```

## 作者

- Jerry Liu

---

⭐ 如果这个项目对你有帮助，请给个 Star！
