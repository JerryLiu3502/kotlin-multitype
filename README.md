# Kotlin MultiType

A Kotlin library for RecyclerView that supports multiple item types, similar to GitHub's MultiType library.

## Features

- Easy registration of multiple item binders
- Type-safe item binding
- Flexible and extensible design
- 100% Kotlin implementation

## Installation

Add the dependency:

```kotlin
dependencies {
    implementation("com.multitype:core:0.1.0")
}
```

## Usage

### 1. Define your item binders

```kotlin
class TextBinder : ItemBinder<String>() {
    override fun getLayoutId(): Int = R.layout.item_text
    override fun getContentType(): Int = 0
    
    override fun onBind(binding: ViewDataBinding, item: String, position: Int) {
        binding.setText(R.id.text, item)
    }
}

class ImageBinder : ItemBinder<ImageItem>() {
    override fun getLayoutId(): Int = R.layout.item_image
    override fun getContentType(): Int = 1
    
    override fun onBind(binding: ViewDataBinding, item: ImageItem, position: Int) {
        binding.load(R.id.image, item.url)
    }
}
```

### 2. Create and configure the adapter

```kotlin
val adapter = MultiTypeAdapter()
adapter.register(TextBinder())
adapter.register(ImageBinder())
```

### 3. Add items

```kotlin
adapter.addItem("Hello")
adapter.addItem(ImageItem("https://example.com/image.png"))
adapter.addItems(listOf("Item 1", "Item 2"))
```

### 4. Set to RecyclerView

```kotlin
recyclerView.adapter = adapter
```

## Architecture

```
┌─────────────────────────────────────────────┐
│              MultiTypeAdapter               │
│  ┌─────────────────────────────────────┐   │
│  │  Binders Map                         │   │
│  │  Type 0 → TextBinder                │   │
│  │  Type 1 → ImageBinder               │   │
│  │  Type 2 → VideoBinder               │   │
│  └─────────────────────────────────────┘   │
└─────────────────────────────────────────────┘
```

## License

MIT License
