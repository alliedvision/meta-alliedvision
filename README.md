# OpenEmbedded/Yocto layer for Allied Vision cameras

Included components:
- Vimba X 2025-1
- Alvium CSI driver 1.2.0
- V4L2Viewer 2.3.0


## Images

| Recipe name | Description |
--------------|-------------|
| av-camera-reference-image-cmdline | Basic command line image with Allied Vision drivers and Vimba X installed | 
| av-camera-reference-image-weston | Image with Wayland GUI. Everything from the command line image plus GUI tools like the VimbaXViewer and V4L2Viewer | 

## Dependencies
This layer depends on:

URI: git://git.openembedded.org/openembedded-core 
branch: scarthgap

URI: git://git.openembedded.org/meta-openembedded
branch: scarthgap

To build the V4L2Viewer qt5 or qt6 is required:

URI: https://github.com/meta-qt5/meta-qt5.git
branch: scarthgap

URI: git://code.qt.io/yocto/meta-qt6.git
branch: 6.9.1

## Reporting issues

Please use the Github issues section of meta-alliedvision for reporting build or runtime issue. When reporting an issue please include following information about your environment:
- Camera model and firmware version
- Host hardware information (SoC, Carrier)


Alternatively you can contact the [Allied Vision Support](https://www.alliedvision.com/en/about-us/contact-us/technical-support-repair-/-rma/).


## Contributing

For submitting patches please use the Github pull-request feature. 