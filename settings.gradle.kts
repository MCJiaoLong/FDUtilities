rootProject.name = "FDUtilities"

include("project:api")
include("project:bukkit")
include("plugin")
include("module")
include("module:TestModule")
findProject(":module:TestModule")?.name = "TestModule"
include("module")
