import cn.fd.fdutilities.module.ModuleExpansion

class TestModule : ModuleExpansion() {

    override val name: String = "Test"
    override val author: String = "MC~蛟龙 (qq1610105206)"
    override val version: String = "0.0.1"

    override fun printMyself() {
        super.printMyself()
        println("测试awa")
    }

}