<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

<#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

</#if>
<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
<#list table.fields as field>
<#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName?substring(1,2)?lower_case}${field.propertyName?substring(2)}" />
</#if>
</#list>
<#list table.commonFields as field><#--生成公共字段 -->
        <result column="${field.name}" property="${field.propertyName?substring(1,2)?lower_case}${field.propertyName?substring(2)}" />
</#list>
<#list table.fields as field>
<#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName?substring(1,2)?lower_case}${field.propertyName?substring(2)}" />
</#if>
</#list>
    </resultMap>

</#if>
<#if baseColumnList>
    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
<#list table.commonFields as field>
        ${field.columnName} as ${field.propertyName?substring(1,2)?lower_case}${field.propertyName?substring(2)},
</#list>
<#list table.fields as field>
    <#if field.keyFlag><#--生成主键排在第一位-->
        ${field.columnName} as ${field.propertyName?substring(1,2)?lower_case}${field.propertyName?substring(2)}
    </#if>
    <#if !field.keyFlag><#--生成普通字段 -->
        ,${field.columnName} as ${field.propertyName?substring(1,2)?lower_case}${field.propertyName?substring(2)}
    </#if>
</#list>
    </sql>

</#if>
</mapper>
