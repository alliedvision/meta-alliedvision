SUMMARY = "Allied Vision Camera Reference Image"

IMAGE_FEATURES += "splash package-management ssh-server-dropbear hwcodecs debug-tweaks"

LICENSE = "BSD-3-Clause"

inherit core-image

CORE_IMAGE_BASE_INSTALL += "vimbax-examples vimbax-firmwareupdater"
CORE_IMAGE_BASE_INSTALL += "vimbax-usbtl vimbax-gigetl"
CORE_IMAGE_BASE_INSTALL += "vmbpy vmbpy-examples"
CORE_IMAGE_BASE_INSTALL += "vmbc-examples"

CORE_IMAGE_BASE_INSTALL:append:aarch64 = " avt-csi2 v4l-utils vimbax-csitl "
 
QB_MEM = "-m 512"

TOOLCHAIN_HOST_TASK:append = " nativesdk-vimbax-dev "