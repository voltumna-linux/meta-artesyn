require recipes-kernel/linux/linux-artesyn_6.18.bb

SRC_URI:append = " \
	file://preempt_rt.cfg \
        file://patch-6.18.57-rt9.patch \
	"
