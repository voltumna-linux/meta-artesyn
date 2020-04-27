DESCRIPTION = "Artesyn Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

LINUX_VERSION ?= "5.4"
LINUX_VERSION_EXTENSION ?= "-artesyn"

SRCREV ?= "148568b5c36821e2f5e26771e88b7c15bc4ae216"
KBRANCH ?= "artesyn-${LINUX_VERSION}"
SRC_URI = "git://github.com/voltumna-linux/linux-artesyn.git;protocol=https;branch=${KBRANCH}"

PV = "${LINUX_VERSION}.28+git${SRCPV}"

COMPATIBLE_MACHINE = "(mvme5100|mvme2500)"

DEPENDS_mvme2500 += "u-boot-mkimage-native"
KERNEL_DEVICETREE_mvme2500 = "fsl/mvme2500.dtb"

# How handle in-kernel configurations which uses config fragments?
# KBUILD_DEFCONFIG_mvme5100 ?= "mvme5100_defconfig"
# KBUILD_DEFCONFIG_mvme2500 ?= "mpc85xx_defconfig"
# KCONFIG_MODE ?= "--alldefconfig"
SRC_URI_append += " \
	file://defconfig \
	file://fix-perf.patch \
	"

SRC_URI_append_mvme5100 += " \
	file://altivec.cfg \
	file://devtmpfs.cfg \
	"

SRC_URI_append_mvme2500 += " \
	file://enable-pci-realloc.cfg \
	file://0014-The-phy-s-compatible-isn-t-necessary-anymore.patch \
	file://Force-the-right-mcpu.patch \
	"

EXTRA_OEMAKE_append_mvme2500 = " HAS_BIARCH=n"

require recipes-kernel/linux/linux-yocto.inc
