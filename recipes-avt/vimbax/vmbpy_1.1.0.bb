SUMMARY = "VmbPy"
DESCRIPTION = "Allied Vision Vimba X Python API"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b5d103fb2e49695c98b4c7522e4e9d87"

DEPENDS = "vimbax"

AVT_GIT_REPO = "VmbPy"
AVT_GIT_BRANCH = "main"
SRCREV = "a972270be6104e8ab54dea3d3d06195aa97e7864"

inherit avt-git python_setuptools_build_meta

S = "${WORKDIR}/git"

PEP517_BUILD_OPTS = "--config-setting=--vmb-dir=${RECIPE_SYSROOT} --config-setting=--plat-name=${TARGET_ARCH}"
EXCLUDE_FROM_SHLIBS = "1"

RDEPENDS:${PN} = "glibc libgcc libstdc++"