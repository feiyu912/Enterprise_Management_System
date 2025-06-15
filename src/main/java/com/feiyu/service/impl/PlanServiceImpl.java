package com.feiyu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyu.entity.Plan;
import com.feiyu.mapper.PlanMapper;
import com.feiyu.service.PlanService;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan> implements PlanService {
} 