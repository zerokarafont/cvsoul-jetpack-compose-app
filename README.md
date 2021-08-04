## 系统最低版本
- 安卓8.0往上
## TODO
- []用户信息本地缓存 Profile
- []跳转返回后恢复之前的状态
    - [] 对于列表的rememberLazyListState在导航间切换时无法记住滚动位置的问题, 可能是个bug?
        + 但是官方明明说到在beat08修复了? [save-and-retain-lazycolumn-scroll-position-while-using-paging-3](https://stackoverflow.com/questions/66744977/save-and-retain-lazycolumn-scroll-position-while-using-paging-3)
        + 暂时的解决方法参考下面的FIXME, 将ListState从不会引发recompose的外部(最近的父NavHost之外)传入

## FIXME
- [x] viewmodel每次随着导航切换都会重新初始化导致无法持久化状态
    - 在未使用以下任何方案的情况下, 栈的跳转, 比如从主页->搜索页, 然后搜索页通过popBackStack()返回可以保存状态,
        同级screen之间navigate跳转无法保存状态
    - [x] 方案一: 将viewmodel的创建提升到最近的父NavHost之外, 这样不会受到导航切换的影响
    - [x] 方案二?: **未验证**使用Hilt依赖注入viewmodel