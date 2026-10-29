SUMMARY = "Allied Vision CSI2 V4L2 driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

AVT_GIT_REPO = "alvium-csi2-driver"
AVT_GIT_BRANCH = "dev"
SRCREV = "d593e148d9804b8d5b4f32b676d7074b320ed407"

inherit avt-git module

PV = "1.5.2+git"

RPROVIDES:${PN} += "kernel-module-avt-csi2"

FILES:${PN} += "${libdir}/modules/${KERNEL_VERSION}/extra/*.ko"

LOCALVERSION = "1.5.2"
KERNEL_MODULE_AUTOLOAD += "avt-csi2"