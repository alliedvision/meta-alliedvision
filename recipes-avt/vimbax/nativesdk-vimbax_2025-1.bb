SUMMARY = "Vimba X 2025-1"
DESCRIPTION = "Allied Vision Vimba X SDK"
LICENSE = "CLOSED"

S = "${WORKDIR}"

inherit nativesdk

do_compile() {
	:
}


do_install() {
    mkdir -p ${D}${datadir}/cmake/OEToolchainConfig.cmake.d
    echo 'list(APPEND CMAKE_PREFIX_PATH "/opt/VimbaX_${PV}")' > ${D}${datadir}/cmake/OEToolchainConfig.cmake.d/vimbax.cmake
}
