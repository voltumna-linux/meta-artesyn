require recipes-kernel/linux/linux-artesyn_4.19.bb

SRC_URI_append += " \
	file://preempt_rt_full.cfg \
	file://patch-4.19.59-rt24.patch \
	"
