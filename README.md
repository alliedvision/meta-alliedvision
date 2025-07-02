# OpenEmbedded/Yocto layer for Allied Vision cameras

Included components:
- Vimba X 2025-1
- Alvium CSI driver 1.2.0
- V4L2Viewer 2.3.0


## Images

| Recipe name | Description |
--------------|-------------|
| av-camera-reference-image-cmdline | Basic command line image with Allied Vision drivers and Vimba X installed | 
| av-camera-reference-image-weston | Image with wayland gui. Everything from the command line image plus gui tools like the VimbaXViewer and V4L2Viewer | 

## Dependencies
This layer depends on:

URI: git://git.openembedded.org/openembedded-core 
branch: scarthgap

To build the v4l2viewer qt5 or qt6 is required:

URI: https://github.com/meta-qt5/meta-qt5.git
branch: scarthgap

URI: git://code.qt.io/yocto/meta-qt6.git
branch: 6.9.1

## Reporting issues

Please use the github issues section of meta-alliedvision for reporting build or runtime issue. When reporting an issue please include following information about your environment:
- Camera model and firmware version
- Host hardware information (SoC, Carrier)


Alternatively you can you write an email to the Allied Vision support: support@alliedvision.com


## Contributing

For submitting patches please use the Github pull-request feature. 