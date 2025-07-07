import type { Metadata } from "next";
import { DM_Sans } from "next/font/google";

const dmSans = DM_Sans({
	variable: "--font-dm-sans",
	subsets: ["latin"],
});

export const metadata: Metadata = {
	title: "GreenTech",
	description: "Marketplace de energia solar",
};

export default function RootLayout({
	children,
}: Readonly<{
	children: React.ReactNode;
}>) {
	return (
		<html lang="en">
			<body className={`${dmSans.variable} ${dmSans.variable} antialiased`}>
				{children}
			</body>
		</html>
	);
}
