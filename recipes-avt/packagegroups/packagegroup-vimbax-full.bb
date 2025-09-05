DESCRIPTION = "Vimba X full installation"

inherit packagegroup

VMB_PACKAGES_API = "vimbax-vmbc vimbax-vmbcpp vmbpy vimbax-vmbimagetransform"

VMB_PACKAGES_TRANSPORT_LAYERS = "vimbax-usbtl vimbax-gigetl"
VMB_PACKAGES_TRANSPORT_LAYERS:append:aarch64 = " vimbax-csitl "

VMB_PACKAGES_EXAMPLES = "vimbax-examples vmbc-examples vmbpy-examples"

VMB_PACKAGES_TOOLS = " \
    vimbax-firmwareupdater \
    ${@bb.utils.contains_any('DISTRO_FEATURES', 'x11 wayland', 'vimbax-viewer', '', d)} \
"

RDEPENDS:${PN} = "\
    ${VMB_PACKAGES_API} \
    ${VMB_PACKAGES_TRANSPORT_LAYERS} \
    ${VMB_PACKAGES_EXAMPLES} \
    ${VMB_PACKAGES_TOOLS} \
"