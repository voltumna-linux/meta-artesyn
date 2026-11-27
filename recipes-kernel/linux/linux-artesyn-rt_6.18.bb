require recipes-kernel/linux/linux-artesyn_6.18.bb

# Note: devtool (latest-version, check-upgrade-status and upgrade) tracks the
# upstream version and SRCREV of the base kernel (see linux-artesyn_6.18.bb);
# the PREEMPT_RT patch below comes from
# https://cdn.kernel.org/pub/linux/kernel/projects/rt/${LINUX_VERSION}/ and has
# to be refreshed by hand, as devtool cannot update files shipped in files/.
SRC_URI:append = " \
	file://preempt_rt.cfg \
        file://patch-6.18.57-rt9.patch \
	"
