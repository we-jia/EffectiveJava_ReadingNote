package com.company;

import lombok.Getter;

@Getter
public enum Level {
    JUNIOR("專員", "最底層的一群人，沒有人在乎他們的死活，可能這輩子就這樣了，嗚嗚"),
    MIDDLE("中級專員", "比專員高一階的人們，領著似乎可以溫飽的薪酬，深怕繼續往上升，這樣就得為了烤雞與其他怪物們拼搏，嗚嗚嗚"),
    SENIOR("高級專員", "在協理眼中似乎是萬能的存在，有著絕對的奴性保證，加班必到、加班費不報，這樣的員工哪裡撿得到"),
    ASSISTANT_MANAGER("副理", "坐著坐著就莫名地跑到這位子的人們，其實他們也不太清楚自己為什麼會在這，只知道好像不用在寫扣了"),
    MANAGER("經理", "鬥爭的開始，當你在這個階級被打下來後，就像是被丟到冰箱一樣，永遠別想有敗部復活戰"),
    SENIOR_MANAGER("協理", "開始往權力的核心靠近了，老實說這階層的人在想什麼作者本人並不清楚，但好像可以在樓梯吃好吃的棒棒糖");

    private String position;

    private String description;

    Level(String position, String description){
       this.position = position;
       this.description = description;
    }
}
