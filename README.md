Neon Support for PhpStorm
=========================================

<!-- Plugin description -->
Fork of the [original plugin](https://github.com/nette-intellij/intellij-neon) that was removed, provides decent support for [Neon](https://github.com/nette/neon) configs.

<!-- Plugin description end -->

Notice
------------
This fork has been created because the original plugin was removed from the marketplace.

If you have any problems with the plugin, [create an issue](https://github.com/Rixafy/NeonSupport/issues/new/choose) or use #nette channel at the [Nette Discord](https://discord.gg/azXxTbuQVq).


Installation
------------
Settings → Plugins → Browse repositories → Find "Neon" → Install Plugin → Apply


Installation from .jar file
------------
Download `instrumented.jar` file from [latest release](https://github.com/Rixafy/NeonSupport/releases) or latest successful [GitHub Actions build](https://github.com/Rixafy/NetteHelpers/actions)


Supported Features
------------------

* Syntax highlighting and code completion for `PHP` classes in `Neon` files

Building
------------

```$xslt
$ ./gradlew buildPlugin
```

Testing in dummy IDE
------------

```$xslt
$ ./gradlew runide
```
