SUMMARY = "VmbC Examples"
DESCRIPTION = "Examples for Allied Vision Vimba X C API"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bdb83a9eaee34f090ead458a3f3dd5b1"

S = "${WORKDIR}/git"

inherit vimbax-sdk

EXTRA_OECMAKE:append = " -DCMAKE_INSTALL_PREFIX=/opt/VmbC_Examples/ "

SRC_URI = "git://github.com/alliedvision/VmbC_Examples.git;protocol=https;branch=dev"
SRCREV = "${AUTOREV}"

FILES:${PN} = "/opt/VmbC_Examples"