SUMMARY = "Vimba X 2025-1"
DESCRIPTION = "Allied Vision Vimba X SDK"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=98d248e64f43a63bafcd46b0c6606ba3"
LICENSE_FLAGS = "commercial"

python () {
    target_arch = d.getVar("TARGET_ARCH")
    if not target_arch in ["aarch64", "x86_64"]:
        bb.fatal(f'Target architecture {target_arch} not supported by Vimba X')
}

SRC_URI = "\
    file://LICENSE.txt;subdir=${S} \
"

SRC_URI:append:aarch64 = "https://downloads.alliedvision.com/VimbaX/VimbaX_Setup-${PV}-Linux_ARM64.tar.gz;name=vimbax-aarch64"
SRC_URI[vimbax-aarch64.sha256sum] = "aad2a34016d2279f907e58d5467990b8db55a9918116b70ba9468dd22d374b47"

SRC_URI:append:x86_64 = "https://downloads.alliedvision.com/VimbaX/VimbaX_Setup-${PV}-Linux64.tar.gz;name=vimbax-x86_64"
# TODO: Adjust checksum
SRC_URI[vimbax-x86_64.sha256sum] = "632482112f4a297ac58b8a048fbb11a336ecbef2ba251ef171c2fefa543e2154"

S = "${WORKDIR}/VimbaX_${PV}"

vimbax_bindir = "/opt/VimbaX_${PV}/bin"
vimbax_libdir = "/opt/VimbaX_${PV}/lib"
vimbax_ctidir = "/opt/VimbaX_${PV}/cti"
vimbax_includedir = "/opt/VimbaX_${PV}/include"

VIMBAX_EXAMPLES = " \
    AsynchronousGrab_VmbC \
    AsynchronousGrab_VmbCPP \
    ListCameras_VmbC \
    ListCameras_VmbCPP \
"

VIMBAX_WAYLAND_GUI_LIBS = " \
    libQt5WaylandClient.so.5 \
    wayland-decoration-client/libbradient.so \
    wayland-graphics-integration-client/libdrm-egl-server.so \
    wayland-graphics-integration-client/libshm-emulation-server.so \
    wayland-graphics-integration-client/libqt-plugin-wayland-egl.so \  
    wayland-shell-integration/libxdg-shell-v6.so \
    wayland-shell-integration/libwl-shell.so \
    wayland-shell-integration/libfullscreen-shell-v1.so \
    wayland-shell-integration/libivi-shell.so \
    wayland-shell-integration/libxdg-shell.so \
    wayland-shell-integration/libxdg-shell-v5.so \
"

VIMBAX_GUI_WAYLAND_RDEPENDS = "wayland wayland-protocols libegl libgl libgles2"

VIMBAX_X11_GUI_LIBS = " \
    libQt5XcbQpa.so.5 \
"
VIMBAX_GUI_X11_RDEPENDS = " \
    libx11 \
    libx11-xcb \
    libxcb \
    libxcb-shm \
    libxcb-randr \
    libxcb-sync \
    libxcb-xkb \
    libxcb-xinerama \
    xcb-util-image \
    xcb-util-renderutil \
    xcb-util-keysyms \
    xcb-util-wm \
    libxkbcommon \
    fontconfig \
    freetype \
    libxcb-render \
    libxcb-shape \
    libxcb-xfixes \
    libxkbcommon-x11 \
"

VIMBAX_GUI_LIBS = " \
    libQt5Core.so.5 \
    libQt5DBus.so.5 \
    libQt5Gui.so.5 \
    libQt5Network.so.5 \
    libQt5PrintSupport.so.5\
    libQt5Qml.so.5 \
    libQt5Svg.so.5 \
    libQt5Widgets.so.5 \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '${VIMBAX_WAYLAND_GUI_LIBS}', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '${VIMBAX_X11_GUI_LIBS}', '', d)} \
"

VIMBAX_FIRMWARE_UPDATER = " \
    VimbaXFirmwareUpdaterConsole \
    ${@bb.utils.contains_any('DISTRO_FEATURES', 'x11 wayland', 'VimbaXFirmwareUpdater', '', d)} \
"


do_install () {
    install -d ${D}${vimbax_bindir}
    install -d ${D}${vimbax_bindir}/wayland-decoration-client
    install -d ${D}${vimbax_bindir}/wayland-graphics-integration-client
    install -d ${D}${vimbax_bindir}/wayland-shell-integration
    install -d ${D}${vimbax_bindir}/platforms
    install -d ${D}${vimbax_bindir}/plugins
    install -d ${D}${vimbax_libdir}
    install -d ${D}${vimbax_libdir}/GenICam
    install -d ${D}${vimbax_ctidir}
    install -m 0777 -d ${D}${vimbax_ctidir}/XMLCache
    install -d ${D}${sysconfdir}/ld.so.conf.d
    install -d ${D}${sysconfdir}/profile.d
    install -d ${D}${sysconfdir}/udev/rules.d/
    install -d ${D}${vimbax_includedir}
    install -d ${D}${vimbax_libdir}/cmake

    for exe in ${VIMBAX_EXAMPLES}; do 
        install -m 0755 ${S}/bin/${exe}  ${D}${vimbax_bindir}
    done

    for exe in ${VIMBAX_FIRMWARE_UPDATER}; do 
        install -m 0755 ${S}/bin/${exe}  ${D}${vimbax_bindir}
    done
   

    install -m 0644 ${S}/api/lib/GenICam/* ${D}${vimbax_libdir}/GenICam
    install -m 0644 ${S}/api/lib/libVmbC.so ${D}${vimbax_libdir}
    install -m 0644 ${S}/api/lib/VmbC.xml ${D}${vimbax_libdir}

    sed -i -e's,<!-- <Path required="false">.</Path> -->,<Path required="true">${vimbax_ctidir}</Path>,g'  ${D}${vimbax_libdir}/VmbC.xml

    echo ${vimbax_libdir} > ${D}${sysconfdir}/ld.so.conf.d/vimbax.conf

    install -m 0644 ${S}/api/lib/libVmbCPP.so ${D}/opt/VimbaX_${PV}/lib

    install -m 0644 ${S}/api/lib/libVmbImageTransform.so ${D}/opt/VimbaX_${PV}/lib

    install -m 0644 ${S}/cti/VimbaCSITL.cti ${D}${vimbax_ctidir}
    install -m 0644 ${S}/cti/VimbaCSITL.xml ${D}${vimbax_ctidir}

    install -m 0644 ${S}/cti/VimbaUSBTL.cti ${D}${vimbax_ctidir}
    install -m 0644 ${S}/cti/VimbaUSBTL.xml ${D}${vimbax_ctidir}
    printf "SUBSYSTEM==\"usb\", ACTION==\"add\", ATTRS{idVendor}==\"1ab2\", ATTRS{idProduct}==\"0001\", MODE=\"0666\"\nSUBSYSTEM==\"usb\", ACTION==\"add\", ATTRS{idVendor}==\"1ab2\", ATTRS{idProduct}==\"ff01\", MODE=\"0666\"\n" > ${D}${sysconfdir}/udev/rules.d/99-vimbax-usbtl.rules

    for lib in ${VIMBAX_GUI_LIBS}; do
        install -m 0644 ${S}/bin/${lib} ${D}${vimbax_bindir}/${lib}
    done
    install -m 0644 ${S}/bin/platforms/* ${D}${vimbax_bindir}/platforms

    install -m 0755 ${S}/bin/VimbaXViewer ${D}${vimbax_bindir}/VimbaXViewer 
    install -m 0644 ${S}/bin/plugins/* ${D}${vimbax_bindir}/plugins

    cp -r ${S}/api/include/* ${D}/${vimbax_includedir}
    cp -r ${S}/api/lib/cmake/* ${D}/${vimbax_libdir}/cmake

    echo "export GENICAM_GENTL64_PATH=${vimbax_ctidir}" > ${D}${sysconfdir}/profile.d/vimbax_tl.sh
    chmod 0755 ${D}${sysconfdir}/profile.d/vimbax_tl.sh
}   



def build_packages_path(d, base_path, var_name):
    return ' '.join([f'{base_path}/{s}' for s in d.getVar(var_name).split(' ') if s.strip() != "" ])
    

PACKAGES = " \
    ${PN}-vmbc \
    ${PN}-vmbc-dev \
    ${PN}-vmbcpp \
    ${PN}-vmbcpp-dev \
    ${PN}-vmbimagetransform \
    ${PN}-vmbimagetransform-dev \
    ${PN}-tlpath \
    ${PN}-csitl \
    ${PN}-usbtl \
    ${PN}-firmwareupdater \
    ${PN}-examples \
    ${PN}-examples-dbg \
    ${PN}-gui-libs \
    ${PN}-viewer \
"

FILES:${PN}-examples = "${@build_packages_path(d, '${vimbax_bindir}', 'VIMBAX_EXAMPLES')}"
FILES:${PN}-examples-dbg = "${@build_packages_path(d, '${vimbax_bindir}/.debug', 'VIMBAX_EXAMPLES')}"
RDEPENDS:${PN}-examples = "${PN}-vmbc ${PN}-vmbcpp ${PN}-vmbimagetransform"

FILES:${PN}-firmwareupdater = "${@build_packages_path(d, '${vimbax_bindir}', 'VIMBAX_FIRMWARE_UPDATER')}"
RDEPENDS:${PN}-firmwareupdater = "${PN}-vmbc ${@bb.utils.contains_any('DISTRO_FEATURES', 'x11 wayland', '${PN}-gui-libs', '', d)}"

FILES:${PN}-vmbc = "/opt/VimbaX_${PV}/lib/GenICam /opt/VimbaX_${PV}/lib/libVmbC.so /opt/VimbaX_${PV}/lib/VmbC.xml /etc/ld.so.conf.d/vimbax.conf"

FILES:${PN}-vmbcpp = "/opt/VimbaX_${PV}/lib/libVmbCPP.so"
RDEPENDS:${PN}-vmbcpp = "${PN}-vmbc"

FILES:${PN}-vmbimagetransform = "/opt/VimbaX_${PV}/lib/libVmbImageTransform.so"

FILES:${PN}-tlpath = "${sysconfdir}/profile.d/vimbax_tl.sh"

FILES:${PN}-csitl = "${vimbax_ctidir}/VimbaCSITL.cti ${vimbax_ctidir}/VimbaCSITL.xml ${vimbax_ctidir}/XMLCache"
RDEPENDS:${PN}-csitl = "glibc libgcc libstdc++ ${PN}-tlpath"

FILES:${PN}-usbtl = "${vimbax_ctidir}/VimbaUSBTL.cti ${vimbax_ctidir}/VimbaUSBTL.xml ${sysconfdir}/udev/rules.d/99-vimbax-usbtl.rules"
RDEPENDS:${PN}-usbtl = "glibc libgcc libstdc++ ${PN}-tlpath"

FILES:${PN}-gui-libs = "${@build_packages_path(d, '${vimbax_bindir}', 'VIMBAX_GUI_LIBS')} ${vimbax_bindir}/platforms"
RDEPENDS:${PN}-gui-libs = " \
    zlib \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '${VIMBAX_GUI_WAYLAND_RDEPENDS}', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '${VIMBAX_GUI_X11_RDEPENDS}', '', d)} \
"

FILES:${PN}-viewer = "${vimbax_bindir}/VimbaXViewer ${vimbax_bindir}/plugins"
RDEPENDS:${PN}-viewer = "${PN}-gui-libs ${PN}-vmbcpp ${PN}-vmbimagetransform"

FILES:${PN}-vmbc-dev = " \
    ${vimbax_includedir}/VmbC \
    ${vimbax_libdir}/cmake/vmb/configs/vmb_c.cmake \
    ${vimbax_libdir}/cmake/vmb/configs/vmb_c-release.cmake \
    ${vimbax_libdir}/cmake/vmb/vmb-config.cmake \
    ${vimbax_libdir}/cmake/vmb/vmb-config-version.cmake \
"
FILES:${PN}-vmbcpp-dev = " \
    ${vimbax_includedir}/VmbCPP \
    ${vimbax_includedir}/VmbCPPConfig \
    ${vimbax_libdir}/cmake/vmb/configs/vmb_cpp.cmake \
    ${vimbax_libdir}/cmake/vmb/configs/vmb_cpp-release.cmake \
    ${vimbax_libdir}/cmake/vmb/configs/vmb_cpp_sources.cmake \
"
FILES:${PN}-vmbimagetransform-dev = " \
    ${vimbax_includedir}/VmbImageTransform \
    ${vimbax_libdir}/cmake/vmb/configs/vmb_imagetransform.cmake \
    ${vimbax_libdir}/cmake/vmb/configs/vmb_imagetransform-release.cmake \
"

SYSROOT_DIRS += "${vimbax_libdir} ${vimbax_includedir}"

INSANE_SKIP:${PN} = "already-stripped"


