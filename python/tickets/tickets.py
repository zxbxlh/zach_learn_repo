"""命令行火车查看器

Usage:
    tickets.py [-gdtkz] <from> <to> <date>

Options:
    -h,--help   显示帮助菜单
    -g          高铁
    -d          动车
    -t          特快
    -k          快速
    -z          直达
"""

from docopt import docopt
from stations import stations
import requests

def cli():
    print('call cli().')
    arguments = docopt(__doc__)
    print(arguments)
    from_station = stations.get(arguments['<from>'])
    to_station = stations.get(arguments['<to>'])
    date = arguments['<date>']
    print(from_station,to_station,date)

    #url = 'https://kyfw.12306.cn/otn/leftTicket/query?purpose_codes=ADULT&queryDate={}&from_station={}&to_station={}'.format(date,from_station,to_station)
    url = 'https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date={}&leftTicketDTO.from_station={}&leftTicketDTO.to_station={}&purpose_codes=ADULT'.format(date,from_station,to_station)
    
    r = requests.get(url,verify=False)
    print(r.json())

if __name__ == '__main__':
    cli()

