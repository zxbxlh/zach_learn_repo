import asyncio

@asyncio.coroutine
def hello():
    print('hello world.')
    y = yield from asyncio.sleep(1)
    print('hello again')

loop = asyncio.get_event_loop()
loop.run_until_complete(hello())
loop.close()
