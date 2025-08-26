SUMMARY = "Allied Vision Video4Linux2 Viewer"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=435d4178fd08b25f9cf911f1c3a0ce1d"


AVT_GIT_REPO = "V4L2Viewer"
AVT_GIT_BRANCH = "dev"
SRCREV = "c3b8566a84620b0424a039e88092c189ccf1afef"

inherit avt-git

DEPENDS:append = " qtbase "

S = "${WORKDIR}/git"

PV = "2.4.0+git${SRCPV}"

do_install() {
	install -d ${D}${bindir}
	install -m 755 ${B}/V4L2Viewer ${D}${bindir}/V4L2Viewer
    chrpath -d ${D}${bindir}/V4L2Viewer
}
