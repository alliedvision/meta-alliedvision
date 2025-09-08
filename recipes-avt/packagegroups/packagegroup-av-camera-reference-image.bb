DESCRIPTION = "Allied Vision camera reference image packages"

inherit packagegroup

REFERENCE_IMAGE_PACKAGES = "packagegroup-vimbax-full"
REFERENCE_IMAGE_PACKAGES:append:aarch64 = " v4l-utils avt-csi2 "

RDEPENDS:${PN} = "${REFERENCE_IMAGE_PACKAGES}"