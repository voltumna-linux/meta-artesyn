FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append_class-target += " \
	file://tweak-MULTIARCH-for-powerpc-linux-gnuspe.patch \
	"
