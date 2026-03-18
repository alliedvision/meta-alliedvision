SUMMARY = "VmbPy"
DESCRIPTION = "Allied Vision Vimba X Python API"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b5d103fb2e49695c98b4c7522e4e9d87"

DEPENDS = "vimbax"

AVT_GIT_REPO = "VmbPy"
AVT_GIT_BRANCH = "main"
SRCREV = "076e4a6dcf642c0044a054d542327ab430008d4e"

inherit avt-git python_setuptools_build_meta

S = "${WORKDIR}/git"

do_install:append() {
    install -d ${D}/opt/VmbPy_Examples

    install -m 0644 ${S}/Examples/* ${D}/opt/VmbPy_Examples
} 

PEP517_BUILD_OPTS = "--config-setting=--vmb-dir=${RECIPE_SYSROOT} --config-setting=--plat-name=${TARGET_ARCH}"
EXCLUDE_FROM_SHLIBS = "1"

PACKAGES += "${PN}-examples"

FILES:${PN}-examples = "/opt/VmbPy_Examples"
RDEPENDS:${PN} = "glibc libgcc libstdc++ python3-ctypes"