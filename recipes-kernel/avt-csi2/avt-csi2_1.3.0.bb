SUMMARY = "Allied Vision CSI2 V4L2 driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

AVT_GIT_REPO = "alvium-csi2-driver"
AVT_GIT_BRANCH = "dev"
SRCREV = "b1121359bf3d09f833bfd332d72939852323b275"

inherit avt-git module

S = "${WORKDIR}/git"

PV = "1.3.0+git${SRCPV}"

RPROVIDES:${PN} += "kernel-module-avt-csi2"

FILES:${PN} += "${libdir}/modules/${KERNEL_VERSION}/extra/*.ko"

LOCALVERSION = "1.3.0"
KERNEL_MODULE_AUTOLOAD += "avt-csi2"