#! /bin/bash  

IFS=$':'
while read code name f1 f2 nbrcom f3 pop
do 
    echo $code $name $nbrcom $pop
mysql --user=root --password=rabah123 rdfdatabase <<EOF
   
insert into inseereg values ('$code','$name','$nbrcom','$pop');

EOF

done <$1 2>error.txt



		
