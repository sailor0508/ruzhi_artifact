package com.ruzhi.demo.test.self.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * Created by chunlong.wchl on 2015/6/3.
 */
@GroupSequence({Default.class, GroupA.class, GroupB.class})
public interface Group {
}
