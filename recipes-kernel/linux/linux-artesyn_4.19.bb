DESCRIPTION = "Artesyn Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

LINUX_VERSION ?= "4.19"
LINUX_VERSION_EXTENSION ?= "-artesyn"

SRCREV ?= "2a87c24e0e914328181cf1f7da9f18f7d830be3f"
KBRANCH ?= "artesyn-${LINUX_VERSION}"
SRC_URI = "git://github.com/voltumna-linux/linux-artesyn.git;protocol=https;branch=${KBRANCH}"

PV = "${LINUX_VERSION}.59+git${SRCPV}"

COMPATIBLE_MACHINE = "(mvme5100|mvme2500|mvme7100)"

DEPENDS_mvme2500 += "u-boot-mkimage-native"

# How handle in-kernel configurations which uses config fragments?
# KBUILD_DEFCONFIG_mvme5100 ?= "mvme5100_defconfig"
# KBUILD_DEFCONFIG_mvme2500 ?= "mpc85xx_defconfig"
# KCONFIG_MODE ?= "--alldefconfig"
SRC_URI_append += " \
	file://defconfig \
	"

SRC_URI_append_mvme5100 += " \
	file://altivec.cfg \
	file://devtmpfs.cfg \
	"

SRC_URI_append_mvme7100 += " \
	file://altivec.cfg \
	file://devtmpfs.cfg \
	file://0001-Enable-L2-cache-on-all-cores.patch \
	file://0002-Enable-RTC-and-PCI.patch \
	file://0003-Try-to-add-PCI-interrupt-definition.patch \
	"

SRC_URI_append_mvme2500 += " \
	file://enable-pci-realloc.cfg \
	file://0014-The-phy-s-compatible-isn-t-necessary-anymore.patch \
	file://Force-the-right-mcpu.patch \
	"

EXTRA_OEMAKE_append_mvme2500 = " HAS_BIARCH=n"

require recipes-kernel/linux/linux-yocto.inc
