# Note: the require must stay at the top so that the values set below (e.g. the
# devtool upstream checks) override the ones coming from linux-yocto.inc
require recipes-kernel/linux/linux-yocto.inc

DESCRIPTION = "Artesyn Linux kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "6.18"
LINUX_VERSION_EXTENSION ?= "-artesyn"

SRCREV ?= "6f86f551c7e06753c3e9ab9590e11e341d8cdd55"
KBRANCH ?= "artesyn-${LINUX_VERSION}"
SRC_URI = "git://github.com/voltumna-linux/linux-artesyn.git;protocol=https;branch=${KBRANCH}"

PV = "${LINUX_VERSION}.57+git${SRCPV}"

# Upstream releases are tagged as v<kernel version>-artesyn (e.g. v6.18.57-artesyn)
# on the commit of the artesyn-${LINUX_VERSION} branch that has to be built: the
# following allows devtool (latest-version, check-upgrade-status and upgrade) to
# find the latest version of the ${LINUX_VERSION} series and the commit to use
# as SRCREV. The optional trailing "\^{}" matches the peeled form of annotated
# tags so that the commit, and not the tag object, gets reported.
# UPSTREAM_CHECK_URI and UPSTREAM_CHECK_REGEX describe the same tag list for the
# checkers working on the GitHub pages (devtool looks at the git tags directly).
UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>${@d.getVar('LINUX_VERSION').replace('.', '\\.')}(?:\.\d+)+)-artesyn(?:\^\{\})?$"
UPSTREAM_STABLE_RELEASE_REGEX = "${@d.getVar('LINUX_VERSION').replace('.', '\\.')}(?:\.\d+)+"
UPSTREAM_CHECK_URI = "https://github.com/voltumna-linux/linux-artesyn/tags"
UPSTREAM_CHECK_REGEX = "archive/refs/tags/v(?P<pver>\d+(\.\d+)+)-artesyn\.tar"

# linux-yocto.inc sets RECIPE_NO_UPDATE_REASON as it assumes kernels are updated
# through a separate process; these recipes are updated with devtool instead.
RECIPE_NO_UPDATE_REASON = ""

COMPATIBLE_MACHINE = "(mvme2500|mvme5100|mvme6100|mvme7100)"

DEPENDS:append:mvme2500 = " u-boot-mkimage-native"

# How handle in-kernel configurations which uses config fragments?
# KBUILD_DEFCONFIG_mvme5100 ?= "mvme5100_defconfig"
# KBUILD_DEFCONFIG_mvme2500 ?= "mpc85xx_defconfig"
# KCONFIG_MODE ?= "--alldefconfig"

SRC_URI:append = " \
	file://defconfig \
	file://enable_compat_time.cfg \
	file://altivec.cfg \
	file://devtmpfs.cfg \
	file://vme.cfg \
	file://enable-pci-realloc.cfg \
	"
