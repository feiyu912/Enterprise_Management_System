package com.feiyu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyu.entity.Opportunity;
import com.feiyu.mapper.OpportunityMapper;
import com.feiyu.service.OpportunityService;
import org.springframework.stereotype.Service;

@Service
public class OpportunityServiceImpl extends ServiceImpl<OpportunityMapper, Opportunity> implements OpportunityService {
} 