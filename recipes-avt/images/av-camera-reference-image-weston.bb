SUMMARY = "Allied Vision Camera Reference Image with Wayland"

IMAGE_FEATURES += "splash package-management ssh-server-openssh hwcodecs weston debug-tweaks"

LICENSE = "BSD-3-Clause"

inherit core-image
require av-camera-reference-image.inc

CORE_IMAGE_BASE_INSTALL += "gtk+3-demo xauth"
CORE_IMAGE_BASE_INSTALL += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)}"
