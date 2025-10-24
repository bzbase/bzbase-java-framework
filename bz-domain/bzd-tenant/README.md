# bzd-tenant

租户领域模块，提供租户聚合根及其管理服务。

- 核心聚合：`Tenant`（包含 `id`、`name`、`status`、`createdAt`）
- 默认规则：新建租户时自动启用并记录创建时间
- 服务接口：`TenantManageService` 提供创建、修改、删除、启用、停用能力
- 资源库接口：`TenantRepository` 负责租户唯一性校验与持久化交互
