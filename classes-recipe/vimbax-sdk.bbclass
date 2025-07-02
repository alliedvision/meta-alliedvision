
inherit cmake

DEPENDS:append = " vimbax "

EXTRA_OECMAKE:append = " -DCMAKE_PREFIX_PATH=$(find ${RECIPE_SYSROOT}/opt -name 'VimbaX_*')"