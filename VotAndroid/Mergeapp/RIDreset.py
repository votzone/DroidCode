#!/usr/bin/env python
# coding=utf-8
__author__ = 'wq'

id_file_path_ori = "oriid.xml"
id_file_path_merge = "mergeid.xml"

import sys, os, re
id_file_path_merge = os.path.join(sys.path[0],id_file_path_merge)
id_file_path_ori = os.path.join(sys.path[0], id_file_path_ori)

print id_file_path_ori, id_file_path_merge

merge_res_dict = {}
'''
{
attr:{com_facebook_foreground_color:0x7f010000,com_facebook_foreground_color:0x7f010000 },
string:{com_facebook_foreground_color:0x7f010000,com_facebook_foreground_color:0x7f010000 }
}
'''


def getMeregResDict():
    with open(id_file_path_merge) as mfile:
        line = mfile.readline()
        while(line):
            if "public" in line:
                t, n, v = obtain_value_tunple(line)
                if merge_res_dict.has_key(t):
                    tmpDict = merge_res_dict[t]
                else:
                    tmpDict = {}
                tmpDict[n] = v
                merge_res_dict[t] = tmpDict
            line = mfile.readline()

def obtain_value_tunple(line):
    findresult = re.findall(r'type="(.+)" name="(.+)" id="0x(.+)"', line, re.S)
    if len(findresult) <= 0:
        return None, None, 0
    [(x, y, z)] = findresult
    znum = int(z, 16)
    return x, y, znum

def format_line(t, n, v):
    strv = hex(v)
    if strv.endswith("L"):
        strv = strv[:-1]
    return '    <public type="%s" name="%s" id="%s" />\n' % (t, n, strv)


def test():
    str = '<public type="attr" name="com_facebook_foreground_color" id="0x7f010000" />'
    str = ""
    findresult = re.findall(r'type="(.+)" name="(.+)" id="0x(.+)"', str, re.S)
    if len(findresult) <=0:
        return "", "", 0
    [(x, y, z)] = findresult
    print x, y, z
    znum = int(z, 16)
    print int(z, 16)
    print hex(znum)


def merge_id_files():
    with open(id_file_path_ori, "r") as ofile:
        with open(id_file_path_ori+"_", "w") as wfile:
            line = ofile.readline()
            lasttype = None
            lastvalue = None
            last_id_type = 0x00010000
            while (line):
                t, n, v = obtain_value_tunple(line)
                if not lasttype:
                    lasttype = t
                if not lastvalue:
                    lastvalue = v

                if lasttype != None:
                    if t == lasttype:
                        if lastvalue < v:
                            lastvalue = v
                    else:
                        # add merge ids
                        if merge_res_dict.has_key(lasttype):
                            tmpDict = merge_res_dict[lasttype]
                            del merge_res_dict[lasttype]
                            upgread_tmp_dict(tmpDict, lastvalue)
                            for key in tmpDict.keys():
                                # base = (lastvalue & 0x0000ffff)+1
                                wfile.writelines(format_line(lasttype, key, tmpDict[key]))

                            if (lastvalue & 0x00ff0000) > (last_id_type & 0x00ff0000):
                                last_id_type = lastvalue & 0x00ff0000

                        lastvalue = v
                        lasttype = t

                if "</resources>"in line:
                    # insert all lines
                    for key in merge_res_dict:
                        tmpDict = merge_res_dict[key]
                        last_id_type = last_id_type + 0x00010000
                        upgread_tmp_dict(tmpDict, last_id_type)
                        for key_name in tmpDict.keys():
                            wfile.writelines(format_line(key, key_name, tmpDict[key_name]))
                        print key

                wfile.writelines(line)
                line = ofile.readline()

def upgread_tmp_dict(tmpDict, base):
    # 取出排序值, 取出类型
    real_value = base &0x0000ffff
    type_value = base &0x00ff0000
    for key in tmpDict.keys():
        tmpDict[key] &= 0xff00ffff
        tmpDict[key] += type_value
        tmpDict[key] += real_value+1


if __name__ == "__main__":

    getMeregResDict()
    for key in merge_res_dict.keys():
        print key
    print "*************"
    merge_id_files()

    # strA = "This is test for get a number 2684561 End"
    # num = re.findall(r'\d.*\d', strA)
    # print num

    # s输出[('23', '34')]
    #
    #             pass